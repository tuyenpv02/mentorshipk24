package com.example.demo.controller;

import com.example.demo.entity.Source;
import com.example.demo.entity.Tag;
import com.example.demo.service.SourceService;
import com.example.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/source")
public class SourceController {

    @Autowired
    SourceService service;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }


    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody Source source) {
        return ResponseEntity.ok(service.add(source));
    }

}
