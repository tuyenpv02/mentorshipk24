package com.example.demo.controller;

import com.example.demo.dto.request.VoteRequest;
import com.example.demo.dto.response.PageResponse;
import com.example.demo.entity.*;
import com.example.demo.service.impl.VoteServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/votes")
public class VoteController {

    @Autowired
    VoteServiceImpl service;

    //Lấy danh sách votes
    @Operation(summary = "Lấy danh sách votes",
            description = "Trả về danh sách votes, hỗ trợ phân trang",
            tags = {"Vote Management"}
    )
    @ApiResponse(
            responseCode = "200",
            description = "Thành công",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PageResponse.class),
                    examples = @ExampleObject(value = """
                            {
                                "pageNumber": 0,
                                "size": 10,
                                "totalPages": 5,
                                "totalElements": 50,
                                "data": [
                                    {
                                        "id": 1,
                                        "user": "User1",
                                        "voteValue": 5,
                                        "timestamp": "2023-10-28T12:34:56"
                                    },
                                    {
                                        "id": 2,
                                        "user": "User2",
                                        "voteValue": 3,
                                        "timestamp": "2023-10-28T13:45:23"
                                    }
                                ]
                            }
                            """)
            ))
    @GetMapping("/votes")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // Lấy danh sách votes của bài đăng thep type"
    @Operation(
            summary = "Lấy danh sách votes của bài đăng thep type",
            description = "Trả về danh sách votes theo accountId theo type( Upvote, Downvote)",
            tags = {"Vote Management"},
            parameters = {
                    @Parameter(name = "postId", description = "ID của bài đăng", required = true),
                    @Parameter(name = "type", description = "Kiểu vote(Upvote, Downvote)", required = true),
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"
                    , content = @Content(mediaType = "application/json"
                    , array = @ArraySchema(schema = @Schema(implementation = Vote.class))))
    })
    @GetMapping("/posts/{postId}/votes/type")
    public ResponseEntity<?> getAllByType(
            @PathVariable(name = "postId") Long postId
            , @RequestParam("type") Integer type) {
        return ResponseEntity.ok(service.getAllVotesByType(postId, type));
    }

    // Lấy danh sách votes của người dùng
    @Operation(
            summary = "Lấy danh sách votes của người dùng",
            description = "Trả về danh sách votes theo accountId",
            tags = {"Vote Management"},
            parameters = {
                    @Parameter(name = "accountId", description = "ID của người dùng", required = true),
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"
                    , content = @Content(mediaType = "application/json"
                    , array = @ArraySchema(schema = @Schema(implementation = Vote.class))))
    })
    @GetMapping("/accounts/{accountId}/votes")
    public ResponseEntity<?> getAllByAccount(
            @PathVariable(name = "accountId") Long accountId) {
        return ResponseEntity.ok(service.getAllByAccountId(accountId));
    }

    // Lấy danh sách votes của bài post
    @Operation(
            summary = "Lấy danh sách votes của bài post",
            description = "Trả về danh sách votes theo postId",
            tags = {"Vote Management"},
            parameters = {
                    @Parameter(name = "postId", description = "ID của bài đăng", required = true),
            }
    )
    @ApiResponse(responseCode = "200", description = "Thành công",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Vote.class))))
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

    // xóa vote theo id
    @Operation(
            summary = "Xóa vote theo ID",
            description = "API này cho phép xóa một vote dựa trên ID của nó.",
            tags = {"Vote Management"},
            parameters = {
                    @Parameter(name = "voteId", description = "ID của vote", required = true)
            }
    )
    @ApiResponse(responseCode = "200", description = "Vote đã được xóa thành công",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Vote.class))
    )
    @DeleteMapping("/votes/{voteId}")
    public ResponseEntity<?> delete(@PathVariable(name = "voteId") Long voteId) {
        if (!service.existsById(voteId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }
        return ResponseEntity.ok(service.deleteById(voteId));
    }

    // Tạo mới vote theo post
    @Operation(
            summary = "Tạo vote theo bài post",
            description = "API này cho phép tạo một vote mới với thông tin đầu vào được cung cấp.",
            tags = {"Vote Management"},
            parameters = {
                    @Parameter(name = "postId", description = "ID của bài đăng", required = true),
            }
    )
    @ApiResponse(responseCode = "201", description = "Vote được tạo thành công",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Vote.class)))
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

    // Tạo mới vote theo comment của post
    @Operation(
            summary = "Tạo vote theo comment trong bài post",
            description = "API này cho phép tạo một vote mới với thông tin đầu vào được cung cấp.",
            tags = {"Vote Management"},
            parameters = {
                    @Parameter(name = "postId", description = "ID của bài đăng", required = true),
                    @Parameter(name = "commentId", description = "ID của bình luận", required = true),
            }
    )
    @ApiResponse(responseCode = "201", description = "Thành công",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Vote.class)))
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

    // cập nhật
    @Operation(summary = "Cập nhật thông tin Vote",
            description = "Cập nhật thông tin một Vote dựa trên ID được cung cấp",
            tags = {"Vote Management"},
            parameters = {
                    @Parameter(name = "voteId", description = "ID của vote", required = true),
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cập nhật thành công",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vote.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "id": 1,
                                        "type": "upvote"
                                    }
                                    """
                            )
                    )),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy Vote với ID này",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{ \"message\": \"Không tìm thấy\" }")
                    ))
    })
    @PutMapping("/votes/{voteId}")
    public ResponseEntity<?> update(@PathVariable("voteId") Long voteId
            , @RequestBody VoteRequest voteRequest) {
        if (!service.existsById(voteId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }
        Vote vote = Vote.builder()
                .type(voteRequest.getType())
                .build();
        return ResponseEntity.ok(service.update(voteId, vote));
    }

}
