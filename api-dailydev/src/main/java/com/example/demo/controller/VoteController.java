package com.example.demo.controller;

import com.example.demo.dto.request.VoteRequest;
import com.example.demo.entity.Account;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.entity.Vote;
import com.example.demo.service.impl.VoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/votes")
public class VoteController {

    @Autowired
    VoteServiceImpl service;

    @GetMapping("/votes")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/posts/{postId}/votes/type")
    public ResponseEntity<?> getAllByType(
            @PathVariable(name = "postId") Long postId
            , @RequestParam("type") Integer type) {
        return ResponseEntity.ok(service.getAllVotesByType(postId, type));
    }

    @GetMapping("/accounts/{accountId}/votes")
    public ResponseEntity<?> getAllByAccount(
            @PathVariable(name = "accountId") Long accountId) {
        return ResponseEntity.ok(service.getAllByAccountId(accountId));
    }

    @GetMapping("/posts/{postId}/votes")
    public ResponseEntity<?> getAllByPost(
            @PathVariable(name = "postId") Long postId
    ) {
        return ResponseEntity.ok(service.getAllByPostId(postId));
    }


//    @GetMapping("/votes/{id}")
//    public ResponseEntity<?> detail(@PathVariable(name = "id") Long id) {
//        if (!service.existsById(id)) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    "Không tìm thấy"
//            );
//        }
//        return ResponseEntity.ok(service.findById(id));
//    }

    @DeleteMapping("/votes/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }
        return ResponseEntity.ok(service.deleteById(id));
    }

    @PostMapping("/posts/{postId}/votes")
    public ResponseEntity<?> addVoteByPost(
            @PathVariable(name = "postId") Long postId
            , @RequestBody VoteRequest voteRequest
    ) {
        Vote vote = Vote.builder()
                .type(voteRequest.getType())
                .account(Account.builder().id(voteRequest.getAccountId()).build())
                .post(Post.builder().id(postId).build())
                .build();
        return ResponseEntity.ok(service.add(vote));
    }

    @PostMapping("/posts/{postId}/comments/{commentId}/votes")
    public ResponseEntity<?> addVoteByComment(
            @PathVariable(name = "postId") Long postId
            , @PathVariable(name = "commentId") Long commentId
            , @RequestBody VoteRequest voteRequest
    ) {
        Vote vote = Vote.builder()
                .type(voteRequest.getType())
                .account(Account.builder().id(voteRequest.getAccountId()).build())
                .post(Post.builder().id(postId).build())
                .comment(Comment.builder().id(commentId).build())
                .build();
        return ResponseEntity.ok(service.add(vote));
    }


    @PutMapping("/votes/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id
            , @RequestBody VoteRequest voteRequest) {
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }
        Vote vote = Vote.builder()
                .type(voteRequest.getType())
                .build();
        return ResponseEntity.ok(service.update(id, vote));
    }

}
