package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.impl.CategoryServiceImpl;
import com.example.demo.service.impl.SourceServiceImpl;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;


@RestController
//@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryServiceImpl service;
    @Autowired
    SourceServiceImpl sourceService;

    @Operation(
            summary = "Lấy danh sách tất cả danh mục",
            description = "Trả về danh sách tất cả các danh mục.",
            tags = {"Category Management"}
    )
    @ApiResponse(
            responseCode = "200",
            description = "Thành công",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = Category.class)))
    )
    @GetMapping("/categories")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(
            summary = "Lấy danh sách danh mục theo nguồn",
            description = "Trả về danh sách các danh mục dựa trên nguồn đã cho.",
            tags = {"Category Management"},
            parameters = {
                    @Parameter(name = "sourceId", description = "Id của source", required = true, example = "10")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Thành công",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Category.class)))
            ),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy nguồn")
    })
    @GetMapping("/sources/{sourceId}/categories")
    public ResponseEntity<?> getAllBySource(@PathVariable("sourceId") Long sourceId) {
        if (!sourceService.existsById(sourceId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy source"
            );
        }
        return ResponseEntity.ok(service.getAllBySource(sourceId));
    }

    @Operation(
            summary = "Chi tiết danh mục",
            description = "Trả về chi tiết của danh mục dựa trên ID.",
            tags = {"Category Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Thành công",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class))
            ),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy danh mục", content = @Content)
    })
    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<?> detail(@PathVariable("categoryId") Long categoryId) {
        if (!service.existsById(categoryId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }
        return ResponseEntity.ok(service.findById(categoryId));
    }

    @Operation(
            summary = "Xóa danh mục theo ID",
            description = "Xóa một danh mục dựa trên ID.",
            tags = {"Category Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Xóa thành công", content = @Content),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy danh mục", content = @Content)
    })
    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<?> delete(@PathVariable("categoryId") Long categoryId) {
        if (!service.existsById(categoryId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }
        return ResponseEntity.ok(service.deleteById(categoryId));
    }

    @Operation(
            summary = "Thêm danh mục mới",
            description = "Thêm một danh mục mới vào hệ thống.",
            tags = {"Category Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tạo thành công",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class))
            )
    })
    @PostMapping("/categories")
    public ResponseEntity<?> save(@RequestBody Category category) {
        return ResponseEntity.ok(service.add(category));
    }

    @Operation(
            summary = "Cập nhật danh mục theo ID",
            description = "Cập nhật thông tin danh mục dựa trên ID.",
            tags = {"Category Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cập nhật thành công",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))
            ),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy danh mục", content = @Content)
    })
    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<?> update(@PathVariable("categoryId") Long categoryId
            , @RequestBody Category category) {
        if (!service.existsById(categoryId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }

        return ResponseEntity.ok(service.update(categoryId, category));
    }
}
