package com.example.demo.controller;

import com.example.demo.entity.Bookmark;
import com.example.demo.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {

    @Autowired
    BookmarkService service;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllByUserId(@PathVariable("userId")Long userId) {
        return ResponseEntity.ok(service.getAllByUserId(userId));
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

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id
            , @RequestBody Bookmark bookmark) {
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }
        return ResponseEntity.ok(service.update(id, bookmark));
    }


}
