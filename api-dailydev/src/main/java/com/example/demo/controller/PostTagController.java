package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.entity.PostTag;
import com.example.demo.entity.Tag;
import com.example.demo.service.impl.PostServiceImpl;
import com.example.demo.service.impl.PostTagServiceImpl;
import com.example.demo.service.impl.TagServiceImpl;
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
//@RequestMapping("/post-tag")
public class PostTagController {

    @Autowired
    PostTagServiceImpl service;

    @Autowired
    PostServiceImpl postService;
    @Autowired
    TagServiceImpl tagService;

    @Operation(
            summary = "Lấy tất cả tag của một bài viết",
            description = "Trả về danh sách các tag được liên kết với một bài viết dựa trên ID bài viết.",
            tags = {"Post Tag Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Thành công",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PostTag.class)))
            ),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy post", content = @Content)
    })
    @GetMapping("/posts/{postID}/post-tag")
    public ResponseEntity<?> getAllPostTagByPost(@PathVariable(name = "postId") Long postId) {
        if (!postService.existsById(postId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy post"
            );
        }
        return ResponseEntity.ok(service.getAllPostTagByByPost(postId));
    }

    @Operation(
            summary = "Lấy tất cả tag của một tag cụ thể",
            description = "Trả về danh sách các tag liên quan đến một tag cụ thể dựa trên ID tag.",
            tags = {"Post Tag Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Thành công",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PostTag.class)))
            ),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy tag", content = @Content)
    })
    @GetMapping("/tags/{tagId}/post-tag")
    public ResponseEntity<?> getAllPostTagByTag(@PathVariable(name = "tagId") Long tagId) {
        if (!postService.existsById(tagId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy tag"
            );
        }
        return ResponseEntity.ok(service.getAllPostTagByByTag(tagId));
    }


    @Operation(
            summary = "Thêm tag cho một bài viết",
            description = "Thêm một tag mới vào bài viết dựa trên ID bài viết.",
            tags = {"Post Tag Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Thêm thành công",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostTag.class))
            ),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy post", content = @Content)
    })
    @PostMapping("/posts/{postId}/tags")
    public ResponseEntity<?> addTagByPost(
            @PathVariable(name = "postId") Long postId,
            @RequestBody PostTag postTag) {
        if (!postService.existsById(postId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy post"
            );
        }
        postTag.setPost(Post.builder().id(postId).build());
        return ResponseEntity.ok(service.add(postTag));
    }

    @Operation(
            summary = "Xóa tag theo ID",
            description = "Xóa một tag dựa trên ID.",
            tags = {"Post Tag Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Xóa thành công", content = @Content),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy tag", content = @Content)
    })
    @DeleteMapping("/post-tag/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }
        return ResponseEntity.ok(service.deleteById(id));
    }
}
