package edu.du.prt.service;

import edu.du.prt.entity.Users; // 엔티티 import 추가!
import edu.du.prt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository usersRepository;

    // 로그인 로직
    public boolean login(String username, String password) {
        Optional<Users> user = usersRepository.findByUsername(username);

        return user.isPresent() && user.get().getPassword().equals(password);
    }

    // 회원가입 (나중에 사용)
    public Users register(Users user) {
        return usersRepository.save(user);
    }
}
