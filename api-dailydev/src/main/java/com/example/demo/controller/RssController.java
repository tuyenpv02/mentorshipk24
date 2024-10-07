package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.RssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rss")
public class RssController {

    @Autowired
    private RssService rssService;

    @PostMapping("/fetch")
    public ResponseEntity<?> fetch(@RequestParam String url) {
        rssService.scraper(url);
        return ResponseEntity.ok("");
    }
}
