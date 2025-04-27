package edu.du.prt.service;

import edu.du.prt.dto.LoginRequest;

import edu.du.prt.entity.Users;
import edu.du.prt.jwt.JwtUtil;
import edu.du.prt.repository.LoginResponse;
import edu.du.prt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;



    public LoginResponse login(LoginRequest loginRequest) {
        Optional<Users> userOpt = userRepository.findByUsername(loginRequest.getUsername());

        if (userOpt.isPresent() && userOpt.get().getPassword().equals(loginRequest.getPassword())) {
            String token = jwtUtil.generateToken(loginRequest.getUsername());
            return new LoginResponse(token, loginRequest.getUsername());
        } else {
            throw new RuntimeException("아이디 또는 비밀번호가 틀렸습니다.");
        }
    }
}
