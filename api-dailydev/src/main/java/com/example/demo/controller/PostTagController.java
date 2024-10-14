package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.entity.PostTag;
import com.example.demo.entity.Source;
import com.example.demo.entity.Tag;
import com.example.demo.service.PostTagService;
import com.example.demo.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post-tag")
public class PostTagController {

    @Autowired
    PostTagService service;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/post")
    public ResponseEntity<?> getAllPostTagByPost(@RequestBody Post post) {
        return ResponseEntity.ok(service.getAllPostTagByByPost(post));
    }

    @GetMapping("/tag")
    public ResponseEntity<?> getAllPostTagByTag(@RequestBody Tag tag) {
        return ResponseEntity.ok(service.getAllPostTagByByTag(tag));
    }

    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody PostTag postTag) {
        return ResponseEntity.ok(service.add(postTag));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }
        return ResponseEntity.ok(service.deleteById(id));
    }
}
