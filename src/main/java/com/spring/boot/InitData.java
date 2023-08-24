package com.spring.boot;

import com.spring.boot.employee.model.User;
import com.spring.boot.employee.repository.UserRepository;
import com.spring.boot.employee.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class InitData {
    private UserService userService;
    private UserRepository userRepository;

    @PostConstruct
    public void initData() {
        this.createUser();
    }

    @PreDestroy
    public void cleanUp() {
        userRepository.deleteAll();
    }

    public void createUser() {
        User user = new User(null, "admin", "admin", null);
        userService.save(user);
    }
}
