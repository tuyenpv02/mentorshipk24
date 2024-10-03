package com.example.demo.controller;

import com.example.demo.entity.Follow;
import com.example.demo.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follows")
public class FollowController {

    @Autowired
    FollowService service;

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getAllUser(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.getAllUser(id));
    }

    @GetMapping("/follow/{id}")
    public ResponseEntity<?> getAllFollow(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.getAllFollow(id));
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
    public ResponseEntity<?> add(@RequestBody Follow Follow) {
        return ResponseEntity.ok(service.add(Follow));
    }

}
