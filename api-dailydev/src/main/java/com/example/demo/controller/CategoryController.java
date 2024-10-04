package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService service;

    @PostMapping("/fetch")
    public ResponseEntity<?> fetch(@RequestBody Category rss) {
        return ResponseEntity.ok(service.fetchRss(rss));
    }
}
