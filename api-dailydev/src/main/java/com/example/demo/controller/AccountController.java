package com.example.demo.controller;

import com.example.demo.dto.SignUpDTO;
import com.example.demo.dto.UserLoginDTO;
import com.example.demo.entity.Account;
import com.example.demo.service.impl.AccountServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountServiceImpl service;

    @Operation(
            summary = "Đăng nhập người dùng",
            description = "Xác thực thông tin đăng nhập của người dùng và trả về token.",
            tags = {"Account Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Thành công",
                    content = @Content
            ),
            @ApiResponse(responseCode = "401", description = "Thông tin đăng nhập không hợp lệ", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        if (service.login(userLoginDTO)) {
            return ResponseEntity.ok("Đăng nhập thành công");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Thông tin đăng nhập không hợp lệ");
        }
    }


    @Operation(
            summary = "Đăng ký người dùng mới",
            description = "Tạo mới tài khoản người dùng. Trả về lỗi nếu email đã được sử dụng.",
            tags = {"Account Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Đăng ký thành công", content = @Content),
            @ApiResponse(responseCode = "400", description = "Email đã được đăng ký", content = @Content)
    })
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUpDTO signUpDTO) {
        if (service.checkEmail(signUpDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Email đã được đăng ký");
        }

        return ResponseEntity.ok(service.signUp(signUpDTO));
    }

    // lấy danh sách account theo page và size
    @Operation(
            summary = "Lấy danh sách tài khoản theo phân trang",
            description = "Trả về danh sách tài khoản với phân trang, hỗ trợ các tham số `page` và `size`.",
            tags = {"Account Management"},
            parameters = {
                    @Parameter(name = "page", description = "Số trang (bắt đầu từ 1)", required = true, example = "1"),
                    @Parameter(name = "size", description = "Kích thước trang, giới hạn từ 5 đến 100", required = true, example = "10")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class)
                    )
            )
    })
    @GetMapping("")
    public ResponseEntity<?> getAll(
            @RequestParam("page") int page
            , @RequestParam("size") int size) {
        if (page < 1) {
            return ResponseEntity.badRequest().body("Page or size is not integer");
        }
        // Thiết lập giá trị cho size
        size = Math.max(5, Math.min(size, 100));
        return ResponseEntity.ok(service.getAll(page - 1, size));
    }

    @Operation(
            summary = "Xem chi tiết tài khoản",
            description = "Trả về chi tiết tài khoản dựa trên ID. Trả về lỗi nếu không tìm thấy.",
            tags = {"Account Management"},
            parameters = {
                    @Parameter(name = "accountId", description = "ID của tài khoản cần lấy thông tin", required = true, example = "1")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Account.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy")
    })
    @GetMapping("/{accountId}")
    public ResponseEntity<?> detail(@PathVariable(name = "accountId") Long accountId) {
        if (!service.existsById(accountId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }
        return ResponseEntity.ok(service.findById(accountId));
    }

    @Operation(
            summary = "Thêm mới tài khoản",
            description = "Tạo mới một tài khoản và trả về đối tượng tài khoản đã tạo.",
            tags = {"Account Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thêm mới thành công",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Account.class)
                    )
            )
    })
    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody Account account) {
        return ResponseEntity.ok(service.add(account));
    }

    @Operation(
            summary = "Cập nhật tài khoản",
            description = "Cập nhật thông tin của một tài khoản dựa trên ID.",
            tags = {"Account Management"},
            parameters = {
                    @Parameter(name = "accountId", description = "ID của tài khoản cần cập nhật", required = true, example = "1")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cập nhật thành công",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Account.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy", content = @Content)
    })
    @PutMapping("/{accountId}")
    public ResponseEntity<?> update(@PathVariable("accountId") Long accountId
            , @RequestBody Account account) {
        if (!service.existsById(accountId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Không tìm thấy"
            );
        }
        return ResponseEntity.ok(service.update(accountId, account));
    }
}
