package edu.du.prt.repository;

import edu.du.prt.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<Users, Long> {
    // 필요한 메서드 추가 가능
    Optional<Users> findByUsername(String username); // 로그인용
}
