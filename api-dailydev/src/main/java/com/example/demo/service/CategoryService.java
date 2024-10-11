package com.example.demo.service;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Post;
import com.example.demo.entity.Category;
import com.example.demo.entity.Source;
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

//    public String fetchRss(Category rss) {
//        Optional<Category> optional = repository.findByName(rss.getName());
//        if(optional.isEmpty()){
//            return "không tìm thấy category";
//        }
//
//        URL feedUrl = null;
//        try {
//            feedUrl = new URL(rss.getLink());
//            SyndFeedInput input = new SyndFeedInput();
//            SyndFeed feed = input.build(new XmlReader(feedUrl));
//            for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
//                Post post = new Post();
//                post.setTitle(entry.getTitle());
//                post.setLink(entry.getLink());
//                post.setDescription(entry.getDescription().getValue());
//                post.setPubDate(LocalDateTime.now());
//                post.setCategory(optional.get());
//
//                postService.add(post);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "ok";
//    }

    public List<Category> getAll() {
        return repository.findAll();
    }

    public List<Category> getAllBySource(Long sourceId) {
        Source source = Source.builder().id(sourceId).build();
        return repository.findAllBySource(source);
    }

    public Category findById(Long id) {
        Optional<Category> optional = repository.findById(id);
        return optional.map(o -> o).orElse(null);
    }

    public Category add(Category category) {
        return repository.save(category);
    }

    public Category update(Long id, Category newCategory) {
        Optional<Category> optional = repository.findById(id);
        return optional.map(o -> {
            o.setName(newCategory.getName());
            o.setLink(newCategory.getLink());
            o.setSource(Source.builder().id(newCategory.getSource().getId()).build());
            return repository.save(o);
        }).orElse(null);
    }

    public Category deleteById(Long id) {
        Optional<Category> optional = repository.findById(id);
        return optional.map(o -> {
            repository.delete(o);
            return o;
        }).orElse(null);
    }

    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }

}
