package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentServiceImpl service;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/post")
    public ResponseEntity<?> getAllByPost(@RequestBody Post post) {
        return ResponseEntity.ok(service.getAllByPost(post));
    }

    @GetMapping("/account")
    public ResponseEntity<?> getAllByAccount(@RequestBody Account account) {
        return ResponseEntity.ok(service.getAllByAccount(account));
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

    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody Comment Comment) {
        return ResponseEntity.ok(service.add(Comment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id
            , @RequestBody Comment Comment) {
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }

        return ResponseEntity.ok(service.update(id, Comment));
    }
}
