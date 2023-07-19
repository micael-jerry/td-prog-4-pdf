package com.spring.boot.controller.mapper;

import com.spring.boot.controller.dto.CreateEmployeeDto;
import com.spring.boot.controller.dto.EmployeeDto;
import com.spring.boot.controller.dto.UpdateEmployeeDto;
import com.spring.boot.model.Employee;
import com.spring.boot.model.Sex;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
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
        employee.setSex(Sex.valueOf(createEmployeeDto.getSex()));
        return employee;
    }

    public Employee toEntity(UpdateEmployeeDto updateEmployeeDto) {
        Date birthday = Date.from(updateEmployeeDto.getBirthday().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Employee employee = new Employee();
        employee.setId(updateEmployeeDto.getId());
        employee.setFirstname(updateEmployeeDto.getFirstname());
        employee.setLastname(updateEmployeeDto.getLastname());
        employee.setBirthday(birthday);
        employee.setId_image(updateEmployeeDto.getId_image());
        employee.setPersonnelNumber(updateEmployeeDto.getPersonnelNumber());
        employee.setSex(Sex.valueOf(updateEmployeeDto.getSex()));
        return employee;
    }

    public EmployeeDto fromEntity(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .firstname(employee.getFirstname())
                .lastname(employee.getLastname())
                .birthday(employee.getBirthday())
                .id_image(employee.getId_image())
                .personnelNumber(employee.getPersonnelNumber())
                .sex(employee.getSex())
                .build();
    }

    public UpdateEmployeeDto fromEntityUpdate(Employee employee) {
        LocalDate birthday = Instant
                .ofEpochMilli(employee.getBirthday().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return UpdateEmployeeDto.builder()
                .id(employee.getId())
                .firstname(employee.getFirstname())
                .lastname(employee.getLastname())
                .birthday(birthday)
                .id_image(employee.getId_image())
                .personnelNumber(employee.getPersonnelNumber())
                .sex(employee.getSex().toString())
                .build();
    }
}
