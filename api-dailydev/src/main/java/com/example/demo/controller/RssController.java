package com.example.demo.controller;

import com.example.demo.dto.RssRequest;
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import com.example.demo.service.RssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rss")
public class RssController {

    @Autowired
    private RssService rssService;

    @Autowired
    CategoryService service;

//    @PostMapping("/fetch-category")
//    public ResponseEntity<?> fetchRssCategory(@RequestBody Category rss) {
//        return ResponseEntity.ok(service.fetchRss(rss));
//    }

    @PostMapping("/url")
    public ResponseEntity<?> fetchRssUrl(@RequestBody RssRequest rssRequest) {
        try {
            return ResponseEntity.ok(rssService.fetchRss(rssRequest));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/source")
    public ResponseEntity<?> fetchSource(@RequestParam String url) {

        return ResponseEntity.ok(rssService.readSource(url));
    }
}
