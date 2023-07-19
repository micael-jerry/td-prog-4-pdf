package com.spring.boot.controller;

import com.spring.boot.controller.dto.CreateEmployeeDto;
import com.spring.boot.controller.dto.EmployeeDto;
import com.spring.boot.controller.dto.UpdateEmployeeDto;
import com.spring.boot.controller.mapper.EmployeeMapper;
import com.spring.boot.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.spring.boot.utils.ModelAttributeName.CREATE_EMPLOYEE_ATTRIBUTE;
import static com.spring.boot.utils.ModelAttributeName.EMPLOYEE_LIST_ATTRIBUTE;
import static com.spring.boot.utils.ModelAttributeName.FILE_EMPLOYEE_ATTRIBUTE;
import static com.spring.boot.utils.ModelAttributeName.UPDATE_EMPLOYEE_ATTRIBUTE;

@Controller
@AllArgsConstructor
@Slf4j
public class EmployeeController {
    private EmployeeMapper employeeMapper;
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public String getEmployees(
            @RequestParam(value = "search", required = false) String search,
            Model model
    ) {
        List<EmployeeDto> employees = employeeService.findAll(search)
                .stream().map(employeeMapper::fromEntity)
                .toList();
        model.addAttribute(EMPLOYEE_LIST_ATTRIBUTE, employees);
        return "employees";
    }

    @GetMapping("/file-employee")
    public String fileEmployee(
            @RequestParam("id") Integer id,
            Model model
    ) {
        EmployeeDto employeeDto = employeeMapper.fromEntity(employeeService.findById(id).get());
        model.addAttribute(FILE_EMPLOYEE_ATTRIBUTE, employeeDto);
        return "fileEmployee";
    }

    @GetMapping("/create-employee")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute(CREATE_EMPLOYEE_ATTRIBUTE, new CreateEmployeeDto());
        return "createEmployee";
    }

    @PostMapping("/employees")
    public String addEmployee(
            @Valid @ModelAttribute(CREATE_EMPLOYEE_ATTRIBUTE) CreateEmployeeDto createEmployeeDto,
            BindingResult result,
            @RequestParam("image") MultipartFile image
    ) throws IOException {
        if (result.hasErrors()) {
            return "createEmployee";
        }
        employeeService.save(employeeMapper.toEntity(createEmployeeDto), image);
        return "redirect:/create-employee";
    }

    @GetMapping("/update-employee")
    public String showUpdateEmployeeForm(Model model, @RequestParam("id") Integer id) {
        UpdateEmployeeDto updateEmployeeDto = employeeMapper.fromEntityUpdate(employeeService.findById(id).get());
        model.addAttribute(UPDATE_EMPLOYEE_ATTRIBUTE, updateEmployeeDto);
        return "updateEmployee";
    }

    @PostMapping("/employees/update")
    public String updateEmployee(
            @ModelAttribute(UPDATE_EMPLOYEE_ATTRIBUTE) UpdateEmployeeDto updateEmployeeDto,
            @RequestParam(value = "image") MultipartFile image
    ) throws IOException {
        employeeService.update(employeeMapper.toEntity(updateEmployeeDto), image);
        return "redirect:/file-employee?id=" + updateEmployeeDto.getId();
    }
}
