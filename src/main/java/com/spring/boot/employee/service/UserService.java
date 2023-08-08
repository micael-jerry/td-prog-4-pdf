package com.spring.boot.employee.service;

import com.spring.boot.employee.model.User;
import com.spring.boot.employee.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }
}
