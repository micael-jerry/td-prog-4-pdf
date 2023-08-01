package com.spring.boot.service;

import com.spring.boot.model.User;
import com.spring.boot.repository.UserRepository;
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
