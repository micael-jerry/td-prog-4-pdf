package com.spring.boot.employee.repository;

import com.spring.boot.employee.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    boolean existsBySessionId(String sessionId);
}
