package edu.du.prt.controller;

import edu.du.prt.entity.Users;
import edu.du.prt.dto.LoginRequest;
import edu.du.prt.dto.RegisterRequest;
import edu.du.prt.repository.UserRepository;
import edu.du.prt.service.AuthService;
import edu.du.prt.jwt.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ 회원가입
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("이미 존재하는 아이디입니다.");
        }

        Users newUser = Users.builder()
                .username(registerRequest.getUsername())
                .password(registerRequest.getPassword())  // (지금은 평문 저장. 나중에 암호화 가능)
                .email(registerRequest.getEmail())
                .name(registerRequest.getName())
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(newUser);
        return ResponseEntity.ok("회원가입 성공!");
    }

    // ✅ 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            return ResponseEntity.ok(authService.login(loginRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ✅ 현재 로그인한 사용자 정보 조회
    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String username = jwtUtil.getUsernameFromToken(token);
            Optional<Users> userOpt = userRepository.findByUsername(username);

            if (userOpt.isPresent()) {
                Users user = userOpt.get();
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.status(401).body("유효하지 않은 토큰입니다.");
    }
}
