package edu.du.prt.service;

import edu.du.prt.entity.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.du.prt.repository.UserRepository;


import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    // 로그인 기능
    public boolean login(String username, String password) {
        Optional<Users> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            return user.getPassword().equals(password);
        }

        return false;
    }
}
