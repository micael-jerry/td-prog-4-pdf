package com.spring.boot.controller.mapper;

import com.spring.boot.controller.dto.CreateEmployeeDto;
import com.spring.boot.controller.dto.EmployeeDto;
import com.spring.boot.controller.dto.UpdateEmployeeDto;
import com.spring.boot.model.Employee;
import com.spring.boot.model.Sex;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
@AllArgsConstructor
public class EmployeeMapper {
    private CinMapper cinMapper;

    public Employee toEntity(CreateEmployeeDto createEmployeeDto) {
        Date birthday = Date.from(createEmployeeDto.getBirthday().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Employee employee = new Employee();
        employee.setFirstname(createEmployeeDto.getFirstname());
        employee.setLastname(createEmployeeDto.getLastname());
        employee.setBirthday(birthday);
        employee.setSex(Sex.valueOf(createEmployeeDto.getSex()));
        employee.setCnapsNumber(createEmployeeDto.getCnapsNumber());
        employee.setChildrenCount(createEmployeeDto.getChildrenCount());
        employee.setCin(cinMapper.toEntity(createEmployeeDto));
        return employee;
    }

    public Employee toEntity(UpdateEmployeeDto updateEmployeeDto) {
        Date birthday = Date.from(updateEmployeeDto.getBirthday().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Employee employee = new Employee();
        employee.setId(updateEmployeeDto.getId());
        employee.setPersonnelNumber(updateEmployeeDto.getPersonnelNumber());
        employee.setFirstname(updateEmployeeDto.getFirstname());
        employee.setLastname(updateEmployeeDto.getLastname());
        employee.setBirthday(birthday);
        employee.setSex(Sex.valueOf(updateEmployeeDto.getSex()));
        employee.setCnapsNumber(updateEmployeeDto.getCnapsNumber());
        employee.setChildrenCount(updateEmployeeDto.getChildrenCount());
        employee.setId_image(updateEmployeeDto.getId_image());
        employee.setCin(cinMapper.toEntity(updateEmployeeDto));
        return employee;
    }

    public EmployeeDto fromEntity(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .personnelNumber(employee.getPersonnelNumber())
                .firstname(employee.getFirstname())
                .lastname(employee.getLastname())
                .birthday(employee.getBirthday())
                .sex(employee.getSex())
                .cnapsNumber(employee.getCnapsNumber())
                .childrenCount(employee.getChildrenCount())
                .id_image(employee.getId_image())
                .cin(cinMapper.fromEntity(employee.getCin()))
                .build();
    }

    public UpdateEmployeeDto fromEntityUpdate(Employee employee) {
        LocalDate birthday = Instant
                .ofEpochMilli(employee.getBirthday().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate cinDeliveryDate = Instant
                .ofEpochMilli(employee.getCin().getCinDeliveryDate().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return UpdateEmployeeDto.builder()
                .id(employee.getId())
                .personnelNumber(employee.getPersonnelNumber())
                .firstname(employee.getFirstname())
                .lastname(employee.getLastname())
                .birthday(birthday)
                .sex(employee.getSex().toString())
                .cnapsNumber(employee.getCnapsNumber())
                .childrenCount(employee.getChildrenCount())
                .id_image(employee.getId_image())
                .cinId(employee.getCin().getId())
                .cinNumber(employee.getCin().getCinNumber())
                .cinDeliveryDate(cinDeliveryDate)
                .cinDeliveryPlace(employee.getCin().getCinDeliveryPlace())
                .build();
    }
}
