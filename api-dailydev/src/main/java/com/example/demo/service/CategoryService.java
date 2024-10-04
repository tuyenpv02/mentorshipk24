package com.example.demo.service;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.entity.Post;
import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private PostService postService;

    public String fetchRss(Category rss) {
        URL feedUrl = null;
        try {
            feedUrl = new URL(rss.getLink());
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));
            for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
                Post post = new Post();
                post.setTitle(entry.getTitle());
                post.setLink(entry.getLink());
                post.setDescription(entry.getDescription().getValue());
                post.setPubDate(LocalDateTime.now());

                postService.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

}
