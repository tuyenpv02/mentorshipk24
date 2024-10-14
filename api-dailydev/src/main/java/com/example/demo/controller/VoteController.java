package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.entity.Post;
import com.example.demo.entity.Vote;
import com.example.demo.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    VoteService service;

    @GetMapping("/type")
    public ResponseEntity<?> getAllByType(@RequestParam("type") Integer type) {
        return ResponseEntity.ok(service.getAllVoteByTyoe(type));
    }

    @GetMapping("/account")
    public ResponseEntity<?> getAllByAccount(@RequestBody Account account) {
        return ResponseEntity.ok(service.getAllByAccountId(account.getId()));
    }

    @GetMapping("/post")
    public ResponseEntity<?> getAllByAccount(@RequestBody Post post) {
        return ResponseEntity.ok(service.getAllByPostId(post.getId()));
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
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
    public ResponseEntity<?> add(@RequestBody Vote vote) {
        return ResponseEntity.ok(service.add(vote));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id
            , @RequestBody Vote vote) {
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }

        return ResponseEntity.ok(service.update(id, vote));
    }
}
