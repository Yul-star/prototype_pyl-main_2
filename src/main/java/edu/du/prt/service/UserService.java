package edu.du.prt.service;

import edu.du.prt.entity.User; // 엔티티 import 추가!
import edu.du.prt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 로그인 로직
    public boolean login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        return user.isPresent() && user.get().getPassword().equals(password);
    }

    // 회원가입 (나중에 사용)
    public User register(User user) {
        return userRepository.save(user);
    }
}
