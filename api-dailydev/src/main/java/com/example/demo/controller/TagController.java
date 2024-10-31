package com.example.demo.controller;


import com.example.demo.entity.Tag;
import com.example.demo.service.impl.TagServiceImpl;
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

import java.util.Scanner;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    TagServiceImpl service;

    @Operation(
            summary = "Lấy danh sách tất cả tags",
            description = "Trả về danh sách tất cả các tags.",
            tags = {"Tag Management"}
    )
    @ApiResponse(responseCode = "200", description = "Thành công",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Tag.class))
            ))
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    //
    @Operation(
            summary = "Xem chi tiết tag theo ID",
            description = "Trả về chi tiết của một tag dựa trên ID.",
            tags = {"Tag Management"},
            parameters = {
                    @Parameter(name = "tagId", description = "ID của tag cần lấy", required = true, example = "1")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Tag.class)
            )),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy",content = @Content)
    })
    @GetMapping("/{tagId}")
    public ResponseEntity<?> detail(@PathVariable(name = "tagId") Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }
        return ResponseEntity.ok(service.findById(id));
    }

    //
    @Operation(
            summary = "Xóa một tag theo ID",
            description = "Xóa một tag dựa trên ID. Trả về thông báo thành công nếu xóa thành công hoặc lỗi nếu không tìm thấy.",
            tags = {"Tag Management"},
            parameters = {
                    @Parameter(name = "tagId", description = "ID của tag cần xóa", required = true, example = "1")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Xóa thành công", content = @Content),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy", content = @Content),
    })
    @DeleteMapping("/{tagId}")
    public ResponseEntity<?> delete(@PathVariable(name = "tagId") Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }
        return ResponseEntity.ok(service.deleteById(id));
    }

    //
    @Operation(
            summary = "Thêm mới một tag",
            description = "Tạo mới một tag và trả về tag vừa tạo.",
            tags = {"Tag Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thêm mới thành công",
                    content = @Content(schema = @Schema(implementation = Tag.class)))
    })
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Tag tag) {
        return ResponseEntity.ok(service.add(tag));
    }

    //
    @Operation(
            summary = "Cập nhật một tag theo ID",
            description = "Cập nhật thông tin của một tag dựa trên ID. Trả về tag sau khi cập nhật.",
            tags = {"Tag Management"},
            parameters = {
                    @Parameter(name = "tagId", description = "ID của tag cần cập nhật", required = true, example = "1")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cập nhật thành công",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Tag.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy", content = @Content)
    })
    @PutMapping("/{tagId}")
    public ResponseEntity<?> update(@PathVariable("tagId") Long id
            , @RequestBody Tag tag) {
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }
        return ResponseEntity.ok(service.update(id, tag));
    }
}
