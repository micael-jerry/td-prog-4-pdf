package com.spring.boot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.boot.model.Employee;
import com.spring.boot.utils.Convert;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class EmployeeController {
    @GetMapping(path = "/employees/post")
    public String anInt(HttpSession session) throws JsonProcessingException {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(null, "a", "fidimalala", new Date()));
        employees.add(new Employee(null, "c", "fidimalala", new Date()));
        session.setAttribute("employees",Convert.employeeListAsString(employees));
        return "employees";
    }

    @GetMapping(path = "/employees")
    public String getEmployees(HttpSession session, Model model) throws JsonProcessingException {
        List<Employee> employees = Convert.stringAsEmployeeList((String) session.getAttribute("employees"));
        model.addAttribute("employees",employees);
        return "employees";
    }
}
