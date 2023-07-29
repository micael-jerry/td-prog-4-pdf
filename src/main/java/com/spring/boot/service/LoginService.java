package com.spring.boot.service;

import com.spring.boot.model.User;
import com.spring.boot.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private UserRepository userRepository;

    public void login(HttpSession session, String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            String sessionId = session.getId();
            user.setSessionId(sessionId);
            userRepository.save(user);
        }
    }

    public boolean isAuthenticated(HttpSession session) {
        String sessionId = session.getId();
        return userRepository.existsBySessionId(sessionId);
    }

    @PostConstruct
    public void createUser() {
        User user = new User(null, "admin", "admin", null);
        userRepository.save(user);
    }
}
