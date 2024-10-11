package com.example.demo.controller;


import com.example.demo.entity.FollowTag;
import com.example.demo.service.FollowTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follow-tag")
public class FollowTagController {

    @Autowired
    FollowTagService service;
    
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getAllFollowTag(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.getAllTagByUserId(id));
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
    public ResponseEntity<?> add(@RequestBody FollowTag FollowTag) {
        return ResponseEntity.ok(service.add(FollowTag));
    }
}
