package com.spring.boot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.boot.controller.dto.CreateEmployeeDto;
import com.spring.boot.controller.dto.EmployeeDto;
import com.spring.boot.controller.mapper.EmployeeMapper;
import com.spring.boot.service.session.EmployeeSessionService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.spring.boot.utils.SessionAndModelAttributeName.CREATE_EMPLOYEE_ATTRIBUTE;
import static com.spring.boot.utils.SessionAndModelAttributeName.EMPLOYEE_LIST_ATTRIBUTE;

@Controller
@AllArgsConstructor
@Slf4j
public class EmployeeController {
    private EmployeeMapper employeeMapper;
    private EmployeeSessionService employeeSessionService;

    @GetMapping("/employees/add")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute(CREATE_EMPLOYEE_ATTRIBUTE, new CreateEmployeeDto());
        return "createEmployee";
    }

    @PostMapping("/employees")
    public String addEmployee(@ModelAttribute(CREATE_EMPLOYEE_ATTRIBUTE) CreateEmployeeDto createEmployeeDto) {
        // Logic to save the employee to the database
        return "redirect:/employees/add";
    }

    @GetMapping(path = "/employees")
    public String getEmployees(HttpSession session, Model model) throws JsonProcessingException {
        List<EmployeeDto> employees = employeeSessionService.findAll(session)
                        .stream().map(employeeMapper::fromEntity)
                        .toList();
        model.addAttribute(EMPLOYEE_LIST_ATTRIBUTE, employees);
        return "employees";
    }
}
