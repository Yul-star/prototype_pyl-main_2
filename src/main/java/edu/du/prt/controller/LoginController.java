package edu.du.prt.controller;


import edu.du.prt.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    // 로그인
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {

        boolean isLogin = userService.login(username, password);

        if (isLogin) {
            session.setAttribute("user", username);
            return "로그인 성공!";
        } else {
            return "아이디 또는 비밀번호가 틀렸어요.";
        }
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "로그아웃 완료!";
    }
}
