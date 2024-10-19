package com.example.demo.service;

import com.example.demo.dto.RssRequest;
import com.example.demo.entity.Category;
import com.example.demo.entity.Post;
import com.example.demo.entity.Source;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.SourceRepository;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RssService {

    @Autowired
    private PostService postService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SourceRepository sourceRepository;

    @Scheduled(fixedDelay = 60 * 60 * 1000)
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
//        System.out.println("Fixed delay task - " + System.currentTimeMillis() / (60 * 1000));
    }

    public List<Category> readSource(String url) {
        Source source = sourceRepository.findByUrl(url);
        if (source == null) {
            source = sourceRepository.save(Source.builder().url(url).build());
        }
        List<Category> lsCategories = new ArrayList<>();
        try {
            // https://www.24h.com.vn/guest/RSS/

            // Lấy và phân ích cú pháp HTML từ trtang
            Document doc = Jsoup.connect(url).get();

            // Chọn tất cả các mục trong danh sách RSS
            Elements rssItems = doc.select("table.rssTbl tbody tr");

            // Duyệt qua từng mục và lấy title và link
            for (Element rssItem : rssItems) {
                // Lấy tên mục từ cột đầu tiên (td đầu tiên)
                String category = rssItem.select("td").first().text();

                // Lấy liên kết RSS từ cột thứ hai (td thứ hai)
                String link = rssItem.select("td a").attr("href");
                if (link.startsWith("//")) {
                    link = "https:" + link;
                }

                Optional<Category> newCategory = categoryRepository.findByNameAndSource(category, source);
                if (newCategory.isEmpty()) {
                    lsCategories.add(categoryRepository.save(Category.builder().name(category).source(source).link(link).build()));
                    continue;
                }
                lsCategories.add(newCategory.get());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsCategories;
    }

    public String fetchRss(RssRequest rssRequest) throws Exception {
        Optional<Category> optional = categoryRepository.findById(rssRequest.getCategoryId());
        if (optional.isEmpty()) {
            return "không tìm thấy category";
        }

        URL feedUrl = null;
        feedUrl = new URL(rssRequest.getUrl());
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

                postService.add(post);
            }
        }

        return "ok";
    }
}
