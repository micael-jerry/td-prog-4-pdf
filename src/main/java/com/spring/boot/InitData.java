package com.spring.boot;

import com.spring.boot.model.User;
import com.spring.boot.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InitData {
    private UserService userService;

    @PostConstruct
    public void createUser() {
        User user = new User(null, "admin", "admin", null);
        userService.save(user);
    }
}
