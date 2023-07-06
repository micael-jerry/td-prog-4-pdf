package com.spring.boot.controller.mapper;

import com.spring.boot.controller.dto.CreateEmployeeDto;
import com.spring.boot.controller.dto.EmployeeDto;
import com.spring.boot.model.Employee;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Date;

@Component
public class EmployeeMapper {
    public Employee toEntity(CreateEmployeeDto createEmployeeDto) {
        Date birthday = Date.from(createEmployeeDto.getBirthday().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Employee employee = new Employee();
        employee.setFirstname(createEmployeeDto.getFirstname());
        employee.setLastname(createEmployeeDto.getLastname());
        employee.setBirthday(birthday);
        return employee;
    }

    public EmployeeDto fromEntity(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .firstname(employee.getFirstname())
                .lastname(employee.getLastname())
                .birthday(employee.getBirthday())
                .build();
    }
}
