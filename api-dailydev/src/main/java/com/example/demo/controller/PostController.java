package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostServiceImpl service;


    @GetMapping("")
    public ResponseEntity<?> getAll(
            @RequestParam("page") int page
            , @RequestParam("size") int size) {
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllByUserId(@PathVariable(name = "userId") Long id) {
        return ResponseEntity.ok(service.getAllByUserId(id));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getAllByCategoryId(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAllByCategoryId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable(name = "id") Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }
        return ResponseEntity.ok(service.findById(id));
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
    public ResponseEntity<?> add(@RequestBody Post Post) {
        return ResponseEntity.ok(service.add(Post));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id
            , @RequestBody Post Post) {
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }

        return ResponseEntity.ok(service.update(id, Post));
    }
}
