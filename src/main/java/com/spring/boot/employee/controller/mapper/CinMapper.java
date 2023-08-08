package com.spring.boot.employee.controller.mapper;

import com.spring.boot.employee.controller.dto.CinDto;
import com.spring.boot.employee.controller.dto.CreateEmployeeDto;
import com.spring.boot.employee.controller.dto.UpdateEmployeeDto;
import com.spring.boot.employee.model.Cin;
import com.spring.boot.employee.utils.Convert;
import org.springframework.stereotype.Component;

@Component
public class CinMapper {
    public Cin toEntity(CreateEmployeeDto createCinDto) {
        Cin cin = new Cin();
        cin.setCinNumber(createCinDto.getCinNumber());
        cin.setCinDeliveryDate(Convert.stringToDateAndFormat(createCinDto.getCinDeliveryDate()));
        cin.setCinDeliveryPlace(createCinDto.getCinDeliveryPlace());
        return cin;
    }

    public Cin toEntity(UpdateEmployeeDto updateEmployeeDto) {
        Cin cin = new Cin();
        cin.setId(updateEmployeeDto.getCinId());
        cin.setCinNumber(updateEmployeeDto.getCinNumber());
        cin.setCinDeliveryDate(Convert.stringToDateAndFormat(updateEmployeeDto.getCinDeliveryDate()));
        cin.setCinDeliveryPlace(updateEmployeeDto.getCinDeliveryPlace());
        return cin;
    }

    public CinDto fromEntity(Cin cin) {
        return CinDto.builder()
                .id(cin.getId())
                .cinNumber(cin.getCinNumber())
                .cinDeliveryDate(cin.getCinDeliveryDate())
                .cinDeliveryPlace(cin.getCinDeliveryPlace())
                .build();
    }
}
