package com.example.demo.controller;

import com.example.demo.dto.response.PageResponse;
import com.example.demo.entity.Bookmark;
import com.example.demo.entity.Post;
import com.example.demo.service.impl.PostServiceImpl;
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
//@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostServiceImpl service;

    @Operation(
            summary = "Lấy danh sách posts của người dùng",
            description = "Trả về danh sách posts, có hỗ trợ phân trang với các tham số page và size.",
            tags = {"Post Management"},
            parameters = {
                    @Parameter(name = "keyword", description = "Tìm kiếm theo title hoặc link"),
                    @Parameter(name = "page", description = "Số trang (bắt đầu từ 1)", required = true, example = "1"),
                    @Parameter(name = "size", description = "Kích thước trang, giới hạn từ 5 đến 100", required = true, example = "10")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Thành công",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PageResponse.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "pageNumber": 1,
                                        "size": 10,
                                        "totalPages": 5,
                                        "totalElements": 50,
                                        "data": [
                                            {
                                                "id": 1,
                                                "title": "Post 1",
                                                "link": "This is the content of post 1",
                                                "description": "This is the content of post 1",
                                                "publishDate": "2024-10-31 10:16:41",
                                                "updateAt": null
                                            }  
                                        ]
                                    }
                                    """)
                    )
            )
    })
    @GetMapping("/posts")
    public ResponseEntity<?> getAll(
            @RequestParam(value = "page", defaultValue = "1")
                    int page,
            @RequestParam(value = "size", defaultValue = "5")
                    int size,
            @RequestParam(value = "keyword", defaultValue = "", required = false)
                    String keyword
    ) {
        if (page < 1) {
            return ResponseEntity.badRequest().body("Vui lòng nhập số lớn hơn 0");
        }
        // Thiết lập giá trị cho size
        size = Math.max(5, Math.min(size, 100));
        return ResponseEntity.ok(service.getAll(page - 1, size, keyword.trim()));
    }

    @Operation(
            summary = "Lấy danh sách posts của một người dùng cụ thể",
            description = "Trả về danh sách posts của người dùng dựa trên userId, có hỗ trợ phân trang với các tham số page và size.",
            tags = {"Post Management"},
            parameters = {
                    @Parameter(name = "userId", description = "ID của người dùng", required = true, example = "1"),
                    @Parameter(name = "page", description = "Số trang (bắt đầu từ 1)", required = true, example = "1"),
                    @Parameter(name = "size", description = "Kích thước trang, giới hạn từ 5 đến 100", required = true, example = "10")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Thành công",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PageResponse.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "pageNumber": 1,
                                        "size": 10,
                                        "totalPages": 5,
                                        "totalElements": 50,
                                        "data": [
                                            {
                                                "id": 1,
                                                "title": "Post 1",
                                                "link": "Link for post 1",
                                                "description": "This is the content of post 1",
                                                "publishDate": "2024-10-31 10:16:41",
                                                "updateAt": null
                                            },
                                            {
                                                "id": 2,
                                                "title": "Post 2",
                                                "link": "Link for post 2",
                                                "description": "This is the content of post 2",
                                                "publishDate": "2024-10-31 10:16:41",
                                                "updateAt": null
                                            }
                                        ]
                                    }
                                    """)
                    )
            )
    })
    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<?> getAllByAccountId(
            @PathVariable(name = "userId") Long userId,
            @RequestParam(value = "page", defaultValue = "1")
                    int page,
            @RequestParam(value = "size", defaultValue = "5")
                    int size) {
        if (page < 1) {
            return ResponseEntity.badRequest().body("Vui lòng nhập số lớn hơn 0");
        }
        // Thiết lập giá trị cho size
        size = Math.max(5, Math.min(size, 100));
        return ResponseEntity.ok(service.getAllByUserId(userId, page, size));
    }

    @Operation(
            summary = "Lấy danh sách posts theo danh mục",
            description = "Trả về danh sách posts thuộc một danh mục dựa trên categoryId, có hỗ trợ phân trang với các tham số page và size.",
            tags = {"Post Management"},
            parameters = {
                    @Parameter(name = "categoryId", description = "ID của danh mục", required = true, example = "1"),
                    @Parameter(name = "page", description = "Số trang (bắt đầu từ 1)", required = true, example = "1"),
                    @Parameter(name = "size", description = "Kích thước trang, giới hạn từ 5 đến 100", required = true, example = "10")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Thành công",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PageResponse.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "pageNumber": 1,
                                        "size": 10,
                                        "totalPages": 5,
                                        "totalElements": 50,
                                        "data": [
                                            {
                                                "id": 1,
                                                "title": "Post 1",
                                                "link": "Link for post 1",
                                                "description": "This is the content of post 1",
                                                "publishDate": "2024-10-31 10:16:41",
                                                "updateAt": null
                                            },
                                            {
                                                "id": 2,
                                                "title": "Post 2",
                                                "link": "Link for post 2",
                                                "description": "This is the content of post 2",
                                                "publishDate": "2024-10-31 10:16:41",
                                                "updateAt": null
                                            }
                                        ]
                                    }
                                    """)
                    )
            )
    })
    @GetMapping("/categories/{categoryId}/posts")
    public ResponseEntity<?> getAllByCategoryId(
            @PathVariable("categoryId") Long categoryId,
            @RequestParam(value = "page", defaultValue = "1")
                    int page,
            @RequestParam(value = "size", defaultValue = "5")
                    int size) {
        if (page < 1) {
            return ResponseEntity.badRequest().body("Vui lòng nhập số lớn hơn 0");
        }
        // Thiết lập giá trị cho size
        size = Math.max(5, Math.min(size, 100));
        return ResponseEntity.ok(service.getAllByCategoryId(categoryId, page, size));
    }

    @Operation(
            summary = "Lấy chi tiết post theo ID",
            description = "Trả về chi tiết của một post dựa trên ID của nó.",
            parameters = {
                    @Parameter(name = "id", description = "ID của post cần lấy chi tiết", required = true, example = "1")
            },
            tags = {"Post Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Thành công",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Post.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "id": 1,
                                        "title": "Sample Post",
                                        "link": "Sample Post",
                                        "guId": "Sample Post",
                                        "description": "This is the content of the sample post.",
                                        "publishDate": "2024-10-31 10:12:56"
                                    }
                                    """)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy",
                    content = @Content(mediaType = "text/plain", examples = @ExampleObject(value = "Không tìm thấy"))
            )
    })
    @GetMapping("/posts/{id}")
    public ResponseEntity<?> detail(@PathVariable(name = "id") Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(
            summary = "Xóa một post theo ID",
            description = "Xóa một post dựa trên ID. Trả về thông báo thành công nếu xóa thành công hoặc thông báo lỗi nếu không tìm thấy.",
            tags = {"Post Management"},
            parameters = {
                    @Parameter(name = "id", description = "ID của post cần xóa", required = true, example = "1")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Xóa thành công", content = @Content),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy", content = @Content),
            @ApiResponse(responseCode = "500", description = "Xóa thất bại", content = @Content)
    })
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }
        return ResponseEntity.ok(service.deleteById(id) ? "Xóa thành công" : "Xóa thất bại");
    }

    @Operation(
            summary = "Tạo mới một post",
            description = "Tạo một post mới và trả về post vừa được tạo.",
            tags = {"Post Management"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Thông tin của post cần tạo",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Post.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "title": "New Post",
                                        "link": "Link for new post",
                                        "description": "Description of new post",
                                        "publishDate": "2024-10-31 10:16:41",
                                        "updateAt": null
                                    }
                                    """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "200", description = "Post được tạo thành công",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Post.class)))
    @PostMapping("/posts")
    public ResponseEntity<?> save(@RequestBody Post Post) {
        return ResponseEntity.ok(service.save(Post));
    }

    @Operation(
            summary = "Cập nhật thông tin của một post theo ID",
            description = "Cập nhật thông tin của post dựa trên ID. Trả về post đã được cập nhật hoặc thông báo lỗi nếu không tìm thấy.",
            tags = {"Post Management"},
            parameters = {
                    @Parameter(name = "id", description = "ID của post cần cập nhật", required = true, example = "1")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Thông tin của post cần cập nhật",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Post.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "title": "Updated Post",
                                        "link": "Updated link",
                                        "description": "Updated description",
                                        "publishDate": "2024-10-31 10:16:41",
                                        "updateAt": null
                                    }
                                    """
                            )
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cập nhật thành công"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy")
    })
    @PutMapping("/posts/{id}")
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
