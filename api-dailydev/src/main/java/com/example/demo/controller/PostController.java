package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostService service;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllByUserId(@PathVariable(name = "userId") Long id) {
        return ResponseEntity.ok(service.getAllByUserId(id));
    }

    @GetMapping("/squad/{squadId}")
    public ResponseEntity<?> getAllBySquadId(@PathVariable(name = "squadId") Long id) {
        return ResponseEntity.ok(service.getAllBySquadId(id));
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
