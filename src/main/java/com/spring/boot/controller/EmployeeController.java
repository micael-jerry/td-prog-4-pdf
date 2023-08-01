package com.spring.boot.controller;

import com.spring.boot.controller.dto.CreateEmployeeDto;
import com.spring.boot.controller.dto.EmployeeDto;
import com.spring.boot.controller.dto.UpdateEmployeeDto;
import com.spring.boot.controller.mapper.EmployeeMapper;
import com.spring.boot.model.Employee;
import com.spring.boot.service.EmployeeService;
import com.spring.boot.service.LoginService;
import com.spring.boot.utils.CsvFileGenerator;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
import static com.spring.boot.utils.ModelAttributeName.EXPORT_URL_PARAMS_ATTRIBUTE;
import static com.spring.boot.utils.ModelAttributeName.FILE_EMPLOYEE_ATTRIBUTE;
import static com.spring.boot.utils.ModelAttributeName.UPDATE_EMPLOYEE_ATTRIBUTE;

@Controller
@AllArgsConstructor
@Slf4j
public class EmployeeController {
    private EmployeeMapper employeeMapper;
    private EmployeeService employeeService;
    private CsvFileGenerator csvFileGenerator;
    private LoginService loginService;

    @GetMapping("/employees")
    public String getEmployees(
            HttpSession session,
            @RequestParam(value = "function_filter", required = false, defaultValue = "") String function,
            @RequestParam(value = "lastname_filter", required = false, defaultValue = "") String lastname,
            @RequestParam(value = "firstname_filter", required = false, defaultValue = "") String firstname,
            @RequestParam(value = "sex_filter", required = false, defaultValue = "") String sex,
            @RequestParam(value = "country_code_filter", required = false, defaultValue = "") String countryCode,
            @RequestParam(value = "start_date", required = false, defaultValue = "") String startDate,
            @RequestParam(value = "departure_date", required = false, defaultValue = "") String departureDate,
            @RequestParam(value = "order_by", required = false, defaultValue = "") String orderBy,
            @RequestParam(value = "order_direction", required = false, defaultValue = "DESC") String direction,
            Model model
    ) {
        if (!loginService.isAuthenticated(session)) {
            return "redirect:/login";
        }
        List<EmployeeDto> employees = employeeService.findAll(function, lastname, firstname, sex, countryCode, startDate, departureDate, orderBy, direction)
                .stream().map(employeeMapper::fromEntity)
                .toList();
        model.addAttribute(EMPLOYEE_LIST_ATTRIBUTE, employees);
        model.addAttribute(EXPORT_URL_PARAMS_ATTRIBUTE, employeeService.exportUrlParams(function, lastname, firstname, sex, countryCode, orderBy, direction));
        return "employees";
    }

    @GetMapping("/employees/export")
    public void export(
            HttpServletResponse response,
            @RequestParam(value = "function_filter", required = false, defaultValue = "") String function,
            @RequestParam(value = "lastname_filter", required = false, defaultValue = "") String lastname,
            @RequestParam(value = "firstname_filter", required = false, defaultValue = "") String firstname,
            @RequestParam(value = "sex_filter", required = false, defaultValue = "") String sex,
            @RequestParam(value = "country_code_filter", required = false, defaultValue = "") String countryCode,
            @RequestParam(value = "start_date", required = false, defaultValue = "") String startDate,
            @RequestParam(value = "departure_date", required = false, defaultValue = "") String departureDate,
            @RequestParam(value = "order_by", required = false, defaultValue = "") String orderBy,
            @RequestParam(value = "order_direction", required = false, defaultValue = "DESC") String direction
    ) throws IOException {
        response.setContentType("text/csv");
        response.addHeader("Content-Disposition", "attachment; filename=\"employees.csv\"");
        List<Employee> employees = employeeService.findAll(function, lastname, firstname, sex, countryCode, startDate, departureDate, orderBy, direction);
        csvFileGenerator.writeEmployeeToCsv(employees, response.getWriter());
    }

    @GetMapping("/file-employee")
    public String viewEmployee(
            HttpSession session,
            @RequestParam("id") Integer id,
            Model model
    ) {
        if (!loginService.isAuthenticated(session)) {
            return "redirect:/login";
        }
        EmployeeDto employeeDto = employeeMapper.fromEntity(employeeService.findById(id).get());
        model.addAttribute(FILE_EMPLOYEE_ATTRIBUTE, employeeDto);
        return "viewEmployee";
    }

    @GetMapping("/create-employee")
    public String showAddEmployeeForm(HttpSession session, Model model) {
        if (!loginService.isAuthenticated(session)) {
            return "redirect:/login";
        }
        model.addAttribute(CREATE_EMPLOYEE_ATTRIBUTE, new CreateEmployeeDto());
        return "createEmployee";
    }

    @PostMapping("/employees")
    public String addEmployee(
            HttpSession session,
            @Valid @ModelAttribute(CREATE_EMPLOYEE_ATTRIBUTE) CreateEmployeeDto createEmployeeDto,
            BindingResult result,
            @RequestParam("image") MultipartFile image
    ) throws IOException {
        if (!loginService.isAuthenticated(session)) {
            return "redirect:/login";
        }
        if (result.hasErrors()) {
            return "createEmployee";
        }
        employeeService.save(employeeMapper.toEntity(createEmployeeDto), image);
        return "redirect:/create-employee";
    }

    @GetMapping("/update-employee")
    public String showUpdateEmployeeForm(HttpSession session, Model model, @RequestParam("id") Integer id) {
        if (!loginService.isAuthenticated(session)) {
            return "redirect:/login";
        }
        UpdateEmployeeDto updateEmployeeDto = employeeMapper.fromEntityUpdate(employeeService.findById(id).get());
        model.addAttribute(UPDATE_EMPLOYEE_ATTRIBUTE, updateEmployeeDto);
        return "updateEmployee";
    }

    @PostMapping("/employees/update")
    public String updateEmployee(
            HttpSession session,
            @ModelAttribute(UPDATE_EMPLOYEE_ATTRIBUTE) UpdateEmployeeDto updateEmployeeDto,
            @RequestParam(value = "image") MultipartFile image
    ) throws IOException {
        if (!loginService.isAuthenticated(session)) {
            return "redirect:/login";
        }
        employeeService.update(employeeMapper.toEntity(updateEmployeeDto), image);
        return "redirect:/file-employee?id=" + updateEmployeeDto.getId();
    }
}
