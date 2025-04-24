package edu.du.prt.controller;


import edu.du.prt.entity.Users;
import edu.du.prt.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";  // register.html 보여줌
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String email,
                           @RequestParam String name) {
        Users user = Users.builder()
                .username(username)
                .password(password)  // 암호화는 나중에!
                .email(email)
                .name(name)
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);  // DB 저장
        return "redirect:/login";  // 가입 후 로그인 페이지로
    }
}
