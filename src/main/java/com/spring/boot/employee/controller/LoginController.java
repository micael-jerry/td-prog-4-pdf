package com.spring.boot.employee.controller;

import com.spring.boot.employee.controller.dto.LoginDto;
import com.spring.boot.employee.service.LoginService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class LoginController {
    private LoginService loginService;

    @GetMapping("/login")
    public String login(HttpSession session, Model model) {
        if (loginService.isAuthenticated(session)) {
            return "redirect:/";
        }
        model.addAttribute("loginObject", new LoginDto());
        return "login";
    }

    @PostMapping("/login")
    public String saveCompany(
            HttpSession session,
            @Valid @ModelAttribute("loginObject") LoginDto loginDto,
            BindingResult result) {
        if (result.hasErrors()) {
            return "login";
        }
        if (loginService.isAuthenticated(session)) {
            return "redirect:/";
        }
        loginService.login(session, loginDto.getUsername(), loginDto.getPassword());
        return "redirect:/login";
    }
}
