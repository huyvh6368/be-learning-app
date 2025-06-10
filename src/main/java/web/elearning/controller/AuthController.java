package web.elearning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.elearning.dto.JwtResponse;
import web.elearning.dto.LoginRequest;
import web.elearning.dto.RefreshTokenRequest;
import web.elearning.dto.ResponseData;
import web.elearning.dto.request.AccountRequest;
import web.elearning.dto.response.AccountResponse;
import web.elearning.service.AccountService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AccountService accountService;

    // Đăng ký user mới
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AccountRequest request) {
        AccountResponse response = accountService.register(request);
        return ResponseEntity.ok(new ResponseData<>(
                HttpStatus.OK.value(),
                "Login Successfully !!",
                response,
                null, null, null, null));
    }

    // login method
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        JwtResponse response = accountService.login(request);
        return ResponseEntity.ok(new ResponseData<>(
                HttpStatus.OK.value(),
                "Login Successfully !!",
                response,
                null, null, null, null));
    }

    // Làm mới token
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        JwtResponse response = accountService.refreshToken(request);
        return ResponseEntity.ok(new ResponseData<>(
                HttpStatus.OK.value(),
                "refresh-token Successfully !!",
                response,
                null, null, null, null));
    }
}
