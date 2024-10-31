package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.service.impl.AccountServiceImpl;
import com.example.demo.service.impl.CommentServiceImpl;
import com.example.demo.service.impl.PostServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentServiceImpl service;
    @Autowired
    PostServiceImpl postService;
    @Autowired
    AccountServiceImpl accountService;

    @Operation(
            summary = "Lấy tất cả bình luận",
            description = "Trả về danh sách tất cả các bình luận.",
            tags = {"Comment Management"}
    )
    @ApiResponse(
            responseCode = "200",
            description = "Thành công",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Comment.class)))
    )
    @GetMapping("/comments")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(
            summary = "Lấy tất cả bình luận của một bài viết",
            description = "Trả về danh sách bình luận dựa trên ID bài viết.",
            tags = {"Comment Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Thành công",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Comment.class)))
            ),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy post", content = @Content)
    })
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<?> getAllByPost(
            @PathVariable(name = "postId") Long postId) {
        if (!postService.existsById(postId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy post"
            );
        }
        return ResponseEntity.ok(service.getAllByPostID(postId));
    }

    @Operation(
            summary = "Lấy tất cả bình luận của một tài khoản",
            description = "Trả về danh sách bình luận dựa trên ID tài khoản.",
            tags = {"Comment Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Thành công",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Comment.class)))
            ),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy account", content = @Content)
    })
    @GetMapping("/accounts/{accountId}/comments")
    public ResponseEntity<?> getAllByAccount(
            @PathVariable(name = "accountId") Long accountId) {
        if (!accountService.existsById(accountId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy account"
            );
        }
        return ResponseEntity.ok(service.getAllByAccount(accountId));
    }

    @Operation(
            summary = "Xóa bình luận theo ID",
            description = "Xóa một bình luận dựa trên ID.",
            tags = {"Comment Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Xóa thành công", content = @Content),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy bình luận", content = @Content)
    })
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }
        return ResponseEntity.ok(service.deleteById(id));
    }

    //    @Operation(
//            summary = "Thêm bình luận vào một bài viết",
//            description = "Thêm một bình luận mới vào bài viết dựa trên ID bài viết.",
//            tags = {"Comment Management"}
//    )
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "Thêm thành công",
//                    content = @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = Comment.class))
//            ),
//            @ApiResponse(responseCode = "404", description = "Không tìm thấy post", content = @Content)
//    })
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<?> save(
            @PathVariable(name = "postId") Long postId,
            @RequestBody Comment comment) {
        if (!postService.existsById(postId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy post"
            );
        }
        comment.setPost(Post.builder().id(postId).build());
        return ResponseEntity.ok(service.add(comment));
    }

    @Operation(
            summary = "Cập nhật bình luận theo ID",
            description = "Cập nhật nội dung của một bình luận dựa trên ID.",
            tags = {"Comment Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cập nhật thành công",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comment.class))
            ),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy bình luận", content = @Content)
    })
    @PutMapping("/comments/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id
            , @RequestBody Comment Comment) {
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }
        return ResponseEntity.ok(service.update(id, Comment));
    }
}
