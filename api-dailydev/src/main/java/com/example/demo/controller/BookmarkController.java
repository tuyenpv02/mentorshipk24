package com.example.demo.controller;

import com.example.demo.entity.Bookmark;
import com.example.demo.service.impl.BookmarkServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/daily/v1")
public class BookmarkController {

    @Autowired
    BookmarkServiceImpl service;

    @Operation(summary = "lấy danh sách bookmark",
            description = "lấy toàn bộ danh sách bookmark trong database",
            tags = {"Bookmark Management"}
    )
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "trả về thành công"
                    , content = @Content(mediaType = "application/json"
                    , array = @ArraySchema(schema = @Schema(implementation = Bookmark.class))))
    )
    @GetMapping("/bookmarks")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }


    @Operation(
            summary = "Lấy danh sách bookmarks của người dùng",
            description = "Trả về danh sách bookmarks theo accountId, có hỗ trợ phân trang với các tham số page và size.",
            tags = {"Bookmark Management"},
            parameters = {
                    @Parameter(name = "accoountId", description = "ID của tài khoản người dùng", required = true),
                    @Parameter(name = "page", description = "Số trang (bắt đầu từ 1)", required = true, example = "1"),
                    @Parameter(name = "size", description = "Kích thước trang, giới hạn từ 5 đến 100", required = true, example = "10")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Danh sách bookmarks được trả về thành công"
                    , content = @Content(mediaType = "application/json"
                    , array = @ArraySchema(schema = @Schema(implementation = Bookmark.class))))
    })
    @GetMapping("/accounts/{accoountId}/bookmarks")
    public ResponseEntity<?> getAllByUserId(
            @PathVariable("accoountId") Long accoountId
            , @RequestParam("page") int page
            , @RequestParam("size") int size) {
        if (page < 1) {
            return ResponseEntity.badRequest().body("nhập page lớn hơn 1");
        }
        if (size < 5) {
            size = 5;
        } else if (size > 100) {
            size = 100;
        }
        return ResponseEntity.ok(service.getAllByUserId(accoountId, page - 1, size));
    }

    @Operation(
            summary = "Xóa bookmark theo ID",
            description = "API này cho phép xóa một bookmark dựa trên ID của nó.",
            tags = {"Bookmark Management"},
            parameters = {
                    @Parameter(name = "bookmarkId", description = "ID của bookmark", required = true)
            }
    )
    @ApiResponse(responseCode = "200", description = "Bookmark đã được xóa thành công")
    @DeleteMapping("/bookmarks/{bookmarkId}")
    public ResponseEntity<?> deleteBookmark(@PathVariable(name = "bookmarkId") Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }
        return ResponseEntity.ok(service.deleteById(id));
    }

    @Operation(
            summary = "Tạo bookmark mới",
            description = "API này cho phép tạo một bookmark mới với thông tin đầu vào được cung cấp.",
            tags = {"Bookmark Management"}
    )
    @ApiResponse(responseCode = "201", description = "Bookmark được tạo thành công",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Bookmark.class)))
    @PostMapping("/bookmarks")
    public ResponseEntity<?> addNewBookmark(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Đối tượng yêu cầu để tạo một bookmark mới", required = true,
            content = @Content(schema = @Schema(implementation = Bookmark.class))
    ) Bookmark bookmark) {
        return ResponseEntity.ok(service.add(bookmark));
    }

}
