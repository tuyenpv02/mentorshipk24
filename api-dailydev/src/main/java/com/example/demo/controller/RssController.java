package com.example.demo.controller;

import com.example.demo.dto.RssRequest;
import com.example.demo.service.impl.CategoryServiceImpl;
import com.example.demo.service.impl.RssServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rss")
public class RssController {

    @Autowired
    private RssServiceImpl rssService;

    @Autowired
    CategoryServiceImpl service;

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
