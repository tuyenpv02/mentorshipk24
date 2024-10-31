package com.example.demo.controller;


import com.example.demo.entity.Account;
import com.example.demo.entity.FollowTag;
import com.example.demo.service.impl.FollowTagServiceImpl;
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
//@RequestMapping("/follow-tag")
public class FollowTagController {

    @Autowired
    FollowTagServiceImpl service;

    @Operation(
            summary = "Lấy danh sách các thẻ theo dõi của người dùng",
            description = "Trả về danh sách các thẻ mà người dùng đang theo dõi dựa trên ID người dùng.",
            tags = {"FollowTag Management"},
            parameters = {
                    @Parameter(name = "userId", description = "ID của người dùng", required = true, example = "1")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FollowTag.class)))),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy")
    })
    @GetMapping("/users/{userId}/tags")
    public ResponseEntity<?> getAllFollowTag(@PathVariable(name = "userId") Long userId) {
        return ResponseEntity.ok(service.getAllTagByUserId(userId));
    }

    @Operation(
            summary = "Xóa một thẻ theo dõi theo ID",
            description = "Xóa một thẻ theo dõi dựa trên ID. Trả về thông báo thành công nếu xóa thành công hoặc lỗi nếu không tìm thấy.",
            tags = {"FollowTag Management"},
            parameters = {
                    @Parameter(name = "id", description = "ID của thẻ theo dõi cần xóa", required = true, example = "1")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Xóa thành công", content = @Content),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy", content = @Content)
    })
    @DeleteMapping("/followTag/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }
        return ResponseEntity.ok(service.deleteById(id));
    }

    @Operation(
            summary = "Thêm mới một thẻ theo dõi",
            description = "Tạo mới một thẻ theo dõi và trả về đối tượng vừa tạo.",
            tags = {"FollowTag Management"},
            parameters = {
                    @Parameter(name = "userId", description = "Id của user", required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thêm mới thành công",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FollowTag.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "id": 1,
                                        "userId": 2,
                                        "tagId": 3
                                    }
                                    """)
                    )
            )
    })
    @PostMapping("/users/{userId}/tags")
    public ResponseEntity<?> save(
            @PathVariable("userId") Long userId,
            @RequestBody FollowTag followTag) {

        followTag.setAccount(Account.builder().id(userId).build());
        return ResponseEntity.ok(service.add(followTag));
    }
}
