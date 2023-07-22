package com.spring.boot.controller.mapper;

import com.spring.boot.controller.dto.CinDto;
import com.spring.boot.controller.dto.CreateEmployeeDto;
import com.spring.boot.controller.dto.UpdateEmployeeDto;
import com.spring.boot.model.Cin;
import com.spring.boot.utils.Convert;
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
