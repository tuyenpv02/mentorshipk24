package com.example.demo.controller;

import com.example.demo.entity.Follow;
import com.example.demo.entity.Post;
import com.example.demo.service.impl.FollowServiceImpl;
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
@RequestMapping("/follows")
public class FollowController {

    @Autowired
    FollowServiceImpl service;

    @Operation(
            summary = "Lấy danh sách người theo dõi của user theo userId",
            description = "Trả về danh sách người được user theo dõi theo ID đã cung cấp.",
            tags = {"Follow Management"},
            parameters = {
                    @Parameter(name = "userId", description = "ID của người dùng", required = true, example = "1")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Follow.class)))),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy", content = @Content)
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllUser(@PathVariable(name = "userId") Long userId) {
        return ResponseEntity.ok(service.getAllUser(userId));
    }

    @Operation(
            summary = "Lấy danh sách follow theo followId",
            description = "Trả về danh sách những người dùng đang theo dõi user.",
            tags = {"Follow Management"},
            parameters = {
                    @Parameter(name = "followId", description = "ID của người dùng", required = true, example = "1")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Follow.class)))),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy", content = @Content)
    })
    @GetMapping("/{followId}")
    public ResponseEntity<?> getAllFollow(@PathVariable(name = "followId") Long followId) {
        return ResponseEntity.ok(service.getAllFollow(followId));
    }

    //
    @Operation(
            summary = "Xóa một follow theo ID",
            description = "Xóa một follow dựa trên ID. Trả về thông báo thành công nếu xóa thành công hoặc lỗi nếu không tìm thấy.",
            tags = {"Follow Management"},
            parameters = {
                    @Parameter(name = "id", description = "ID của follow cần xóa", required = true, example = "1")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Xóa thành công", content = @Content),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByUserId(@PathVariable(name = "id") Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }
        return ResponseEntity.ok(service.deleteById(id));
    }

    @Operation(
            summary = "Thêm mới một follow",
            description = "Tạo mới một follow và trả về đối tượng follow vừa tạo.",
            tags = {"Follow Management"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Thông tin của post cần tạo",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Follow.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thêm mới thành công",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Follow.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "id": 1,
                                        "userId": 2,
                                        "followedId": 3
                                    }
                                    """)
                    )
            )
    })
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Follow Follow) {
        return ResponseEntity.ok(service.add(Follow));
    }

}
