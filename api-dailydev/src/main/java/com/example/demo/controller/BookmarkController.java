package com.example.demo.controller;

import com.example.demo.entity.Bookmark;
import com.example.demo.service.impl.BookmarkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {

    @Autowired
    BookmarkServiceImpl service;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllByUserId(
            @PathVariable("userId") Long userId
            , @RequestParam("page") int page
            , @RequestParam("size") int size) {
        if (page < 1 || size < 1) {
            return ResponseEntity.badRequest().body("Page or size is not integer");
        }
        return ResponseEntity.ok(service.getAllByUserId(userId, page -1 , size));
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
    public ResponseEntity<?> add(@RequestBody Bookmark bookmark) {
        return ResponseEntity.ok(service.add(bookmark));
    }

}
