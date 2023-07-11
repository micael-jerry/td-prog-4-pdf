package com.spring.boot.controller;

import com.spring.boot.controller.dto.CreateEmployeeDto;
import com.spring.boot.controller.dto.EmployeeDto;
import com.spring.boot.controller.mapper.EmployeeMapper;
import com.spring.boot.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.spring.boot.utils.ModelAttributeName.CREATE_EMPLOYEE_ATTRIBUTE;
import static com.spring.boot.utils.ModelAttributeName.EMPLOYEE_LIST_ATTRIBUTE;

@Controller
@AllArgsConstructor
@Slf4j
public class EmployeeController {
    private EmployeeMapper employeeMapper;
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public String getEmployees(Model model) {
        List<EmployeeDto> employees = employeeService.findAll()
                .stream().map(employeeMapper::fromEntity)
                .toList();
        model.addAttribute(EMPLOYEE_LIST_ATTRIBUTE, employees);
        return "employees";
    }

    @GetMapping("/create-employee")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute(CREATE_EMPLOYEE_ATTRIBUTE, new CreateEmployeeDto());
        return "createEmployee";
    }

    @GetMapping("/fiche-employee")
    public String ficheEmployee(
            @RequestParam("id") Integer id,
            Model model
    ) {
        EmployeeDto employeeDto = employeeMapper.fromEntity(employeeService.findById(id).get());
        model.addAttribute("ficheEmployee",employeeDto);
        return "ficheEmployee";
    }

    @PostMapping("/employees")
    public String addEmployee(
            @ModelAttribute(CREATE_EMPLOYEE_ATTRIBUTE) CreateEmployeeDto createEmployeeDto,
            @RequestParam("image") MultipartFile image,
            Model model
    ) throws IOException {
        employeeService.save(employeeMapper.toEntity(createEmployeeDto), image);
        return "redirect:/create-employee";
    }
}
