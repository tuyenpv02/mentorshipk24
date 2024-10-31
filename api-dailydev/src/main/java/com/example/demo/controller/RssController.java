package com.example.demo.controller;

import com.example.demo.dto.RssRequest;
import com.example.demo.dto.response.PageResponse;
import com.example.demo.entity.Category;
import com.example.demo.entity.Vote;
import com.example.demo.service.impl.CategoryServiceImpl;
import com.example.demo.service.impl.RssServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rss")
public class RssController {

    @Autowired
    private RssServiceImpl rssService;

    @Autowired
    CategoryServiceImpl service;

//    @PostMapping("/fetch-category")
//    public ResponseEntity<?> fetchRssCategory(@RequestBody Category rss) {
//        return ResponseEntity.ok(service.fetchRss(rss));
//    }

    // đọc link rss, lấy posts
    @Operation(
            summary = "Đọc link rss",
            description = "API này cho phép lấy các bài post từ link rss được cung cấp.",
            tags = "Rss Management"
    )
    @ApiResponse(responseCode = "201", description = "Thành công",
            content = @Content(
                    mediaType = "application/json",
//                    schema = @Schema(implementation = PageResponse.class),
                    examples = @ExampleObject(value = "ok")
            ))
    @PostMapping("/url")
    public ResponseEntity<?> fetchRssUrl(@RequestBody RssRequest rssRequest) {
        try {
            return ResponseEntity.ok(rssService.fetchRss(rssRequest));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @Operation(summary = "Đọc source",
            description = "Lấy danh sách rss từ link source",
            tags = {"Rss Management"},
            parameters = {
                    @Parameter(name = "source", description = "Link source rss", example = "https://www.24h.com.vn/guest/RSS/", required = true),
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "thành công",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Category.class))
                    ))
    })
    @PostMapping("/source")
    public ResponseEntity<?> fetchSource(@RequestParam("source") String url) {
        return ResponseEntity.ok(rssService.readSource(url));
    }
}
