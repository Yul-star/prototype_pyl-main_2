package edu.du.prt.controller;

import edu.du.prt.entity.Users;
import edu.du.prt.service.UserService;
import edu.du.prt.dto.LoginRequest;
import edu.du.prt.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "http://localhost:5173", allowCredentials = true) - webconfig에서 이미 실행중
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users user) {
        try {
            userService.register(user);  // 회원가입 처리
            return ResponseEntity.ok("회원가입 성공!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입 실패 😥");
        }
    }

    // 로그인 → JWT 토큰 발급
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        boolean isValid = userService.login(loginRequest.getUsername(), loginRequest.getPassword());

        if (isValid) {
            // 토큰 생성 후 반환
            String token = jwtUtil.generateToken(loginRequest.getUsername());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패!");
        }
    }

    // 로그아웃 → 프론트에서 토큰 삭제만 하면 됨 (백엔드는 처리 안함)
    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        // JWT는 서버에 세션이 없기 때문에 프론트에서 토큰을 삭제하면 됨
        return ResponseEntity.ok("로그아웃 완료!");
    }
}
