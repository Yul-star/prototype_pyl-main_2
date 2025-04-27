package edu.du.prt.controller;


import edu.du.prt.dto.LoginRequest;
import edu.du.prt.jwt.JwtUtil;
import edu.du.prt.repository.UserRepository;
import edu.du.prt.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private LoginService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<UserRepository> login(@RequestBody LoginRequest loginRequest) {
        UserRepository response = authService.login(LoginRequest);
        return ResponseEntity.ok(response);
    }

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("이미 존재하는 아이디입니다.");
        }

        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role("USER")
                .build();

        userRepository.save(user);
        return ResponseEntity.ok("회원가입 성공!");
    }

    // 현재 로그인한 사용자 정보
    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String username = jwtUtil.extractUsername(token);

            return userRepository.findByUsername(username)
                    .map(user -> ResponseEntity.ok(new LoginResponse(
                            token,
                            user.getUsername(),
                            user.getRole(),
                            user.getEmail()
                    )))
                    .orElse(ResponseEntity.notFound().build());
        }
        return ResponseEntity.badRequest().body("토큰이 유효하지 않습니다.");
    }
}
