package com.spring.boot.employee.controller;

import com.spring.boot.employee.service.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {
    LoginService loginService;

    @GetMapping("/")
    public String home(HttpSession session) {
        if (!loginService.isAuthenticated(session)) {
            return "redirect:/login";
        }
        return "home";
    }
}
