package com.spring.boot.employee.controller.mapper;

import com.spring.boot.employee.controller.dto.CreateEmployeeDto;
import com.spring.boot.employee.controller.dto.EmployeeDto;
import com.spring.boot.employee.controller.dto.EmployeeExportDto;
import com.spring.boot.employee.controller.dto.UpdateEmployeeDto;
import com.spring.boot.employee.model.Employee;
import com.spring.boot.employee.model.Sex;
import com.spring.boot.employee.model.SocioProfessionalCategory;
import com.spring.boot.employee.utils.Convert;
import com.spring.boot.employee.utils.DateUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeMapper {
    private CinMapper cinMapper;
    private EmailMapper emailMapper;
    private PhoneMapper phoneMapper;
    private AddressMapper addressMapper;

    public Employee toEntity(CreateEmployeeDto createEmployeeDto) {
        Employee employee = new Employee();
        employee.setFirstname(createEmployeeDto.getFirstname());
        employee.setLastname(createEmployeeDto.getLastname());
        employee.setBirthday(Convert.stringToDateAndFormat(createEmployeeDto.getBirthday()));
        employee.setSex(Sex.valueOf(createEmployeeDto.getSex()));
        employee.setCnapsNumber(createEmployeeDto.getCnapsNumber());
        employee.setChildrenCount(createEmployeeDto.getChildrenCount());
        employee.setSocioProfessionalCategory(SocioProfessionalCategory.valueOf(createEmployeeDto.getSocioProfessionalCategory()));
        employee.setFunction(createEmployeeDto.getFunction());
        employee.setStartDate(Convert.stringToDateAndFormat(createEmployeeDto.getStartDate()));
        employee.setAddress(addressMapper.toEntity(createEmployeeDto));
        employee.setCin(cinMapper.toEntity(createEmployeeDto));
        employee.setPersonalEmail(emailMapper.toEntity(null, createEmployeeDto.getPersonalEmail()));
        employee.setProfessionalEmail(emailMapper.toEntity(null, createEmployeeDto.getProfessionalEmail()));
        employee.setPhones(phoneMapper.toEntity(createEmployeeDto.getPhones()));
        return employee;
    }

    public Employee toEntity(UpdateEmployeeDto updateEmployeeDto) {
        Employee employee = new Employee();
        employee.setId(updateEmployeeDto.getId());
        employee.setPersonnelNumber(updateEmployeeDto.getPersonnelNumber());
        employee.setFirstname(updateEmployeeDto.getFirstname());
        employee.setLastname(updateEmployeeDto.getLastname());
        employee.setBirthday(Convert.stringToDateAndFormat(updateEmployeeDto.getBirthday()));
        employee.setSex(Sex.valueOf(updateEmployeeDto.getSex()));
        employee.setCnapsNumber(updateEmployeeDto.getCnapsNumber());
        employee.setChildrenCount(updateEmployeeDto.getChildrenCount());
        employee.setSocioProfessionalCategory(SocioProfessionalCategory.valueOf(updateEmployeeDto.getSocioProfessionalCategory()));
        employee.setFunction(updateEmployeeDto.getFunction());
        employee.setStartDate(Convert.stringToDateAndFormat(updateEmployeeDto.getStartDate()));
        employee.setDepartureDate(Convert.stringToDateAndFormat(updateEmployeeDto.getDepartureDate()));
        employee.setId_image(updateEmployeeDto.getId_image());
        employee.setAddress(addressMapper.toEntity(updateEmployeeDto));
        employee.setCin(cinMapper.toEntity(updateEmployeeDto));
        employee.setPersonalEmail(emailMapper.toEntity(updateEmployeeDto.getPersonalEmailId(), updateEmployeeDto.getPersonalEmail()));
        employee.setProfessionalEmail(emailMapper.toEntity(updateEmployeeDto.getProfessionalEmailId(), updateEmployeeDto.getProfessionalEmail()));
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
                .socioProfessionalCategory(employee.getSocioProfessionalCategory())
                .function(employee.getFunction())
                .startDate(employee.getStartDate())
                .departureDate(employee.getDepartureDate())
                .id_image(employee.getId_image())
                .address(addressMapper.fromEntity(employee.getAddress()))
                .cin(cinMapper.fromEntity(employee.getCin()))
                .personalEmail(emailMapper.fromEntity(employee.getPersonalEmail()))
                .professionalEmail(emailMapper.fromEntity(employee.getProfessionalEmail()))
                .phones(phoneMapper.fromEntity(employee.getPhones()))
                .build();
    }

    public UpdateEmployeeDto fromEntityUpdate(Employee employee) {
        return UpdateEmployeeDto.builder()
                .id(employee.getId())
                .personnelNumber(employee.getPersonnelNumber())
                .firstname(employee.getFirstname())
                .lastname(employee.getLastname())
                .birthday(Convert.dateToStringAndFormat(employee.getBirthday()))
                .sex(employee.getSex().toString())
                .cnapsNumber(employee.getCnapsNumber())
                .childrenCount(employee.getChildrenCount())
                .socioProfessionalCategory(employee.getSocioProfessionalCategory().toString())
                .function(employee.getFunction())
                .startDate(Convert.dateToStringAndFormat(employee.getStartDate()))
                .departureDate(Convert.dateToStringAndFormat(employee.getDepartureDate()))
                .id_image(employee.getId_image())
                .addressId(employee.getAddress().getId())
                .addressHouse(employee.getAddress().getHouse())
                .addressStreet(employee.getAddress().getStreet())
                .addressCity(employee.getAddress().getCity())
                .addressZipCode(String.valueOf(employee.getAddress().getZipCode()))
                .cinId(employee.getCin().getId())
                .cinNumber(employee.getCin().getCinNumber())
                .cinDeliveryDate(Convert.dateToStringAndFormat(employee.getCin().getCinDeliveryDate()))
                .cinDeliveryPlace(employee.getCin().getCinDeliveryPlace())
                .personalEmailId(employee.getPersonalEmail().getId())
                .personalEmail(employee.getPersonalEmail().getAddress())
                .professionalEmailId(employee.getProfessionalEmail().getId())
                .professionalEmail(employee.getProfessionalEmail().getAddress())
                .build();
    }

    public EmployeeExportDto toExport(EmployeeDto employeeDto) {
        return EmployeeExportDto.builder()
                .personnelNumber(employeeDto.getPersonnelNumber())
                .firstname(employeeDto.getFirstname())
                .lastname(employeeDto.getLastname())
                .age(DateUtil.ageCalculator(employeeDto.getBirthday()))
                .id_image(employeeDto.getId_image())
                .cnapsNumber(employeeDto.getCnapsNumber())
                .startDate(employeeDto.getStartDate())
                .departureDate(employeeDto.getDepartureDate())
                .professionalEmail(employeeDto.getProfessionalEmail())
                .build();
    }
}
