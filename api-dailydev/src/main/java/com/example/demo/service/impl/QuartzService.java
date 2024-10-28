package com.example.demo.service.impl;

import com.example.demo.dto.RssRequest;
import com.example.demo.entity.Category;
import com.example.demo.entity.Post;
import com.example.demo.repository.CategoryRepository;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class QuartzService implements Job {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostServiceImpl postService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        scheduleFixedDelayTask();
    }

    public void scheduleFixedDelayTask() {
        List<Category> categories = categoryRepository.findAll();
        categories.forEach(o -> {
            RssRequest rssRequest = new RssRequest(o.getLink(), o.getSource().getId());
            try {
                fetchRss(rssRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public String fetchRss(RssRequest rssRequest) throws Exception {
        Optional<Category> optional = categoryRepository.findById(rssRequest.getCategoryId());
        if (optional.isEmpty()) {
            return "không tìm thấy category";
        }

        URL feedUrl = new URL(rssRequest.getUrl());
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedUrl));

        for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {

            if (postService.findByGuId(entry.getUri()) == null) {
                Post post = new Post();
                post.setTitle(entry.getTitle());
                post.setLink(entry.getLink());
                post.setGuId(entry.getUri());
                post.setDescription(entry.getDescription().getValue());
                post.setPubDate(LocalDateTime.now());
                post.setCategory(Category.builder().id(optional.get().getId()).build());

                postService.save(post);
            }
        }
        return "ok";
    }

}
