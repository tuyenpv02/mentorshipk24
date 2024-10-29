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
            @RequestParam(value = "page", defaultValue = "1") int page
            , @RequestParam(value = "size", defaultValue = "5") int size
            , @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword
    ) {
        if (page < 1 || size < 1) {
            return ResponseEntity.badRequest().body("Vui lòng nhập số lớn hơn 0");
        }
        return ResponseEntity.ok(service.getAll(page -1 , size, keyword.trim()));
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
        return ResponseEntity.ok(service.save(Post));
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
