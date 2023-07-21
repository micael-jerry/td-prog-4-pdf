package com.spring.boot.controller.mapper;

import com.spring.boot.controller.dto.CreateEmployeeDto;
import com.spring.boot.model.Cin;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Date;

@Component
public class CinMapper {
    public Cin toEntity(CreateEmployeeDto createCinDto) {
        Date deliveryDate = Date.from(createCinDto.getCinDeliveryDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Cin cin = new Cin();
        cin.setCinNumber(createCinDto.getCinNumber());
        cin.setCinDeliveryDate(deliveryDate);
        cin.setCinDeliveryPlace(createCinDto.getCinDeliveryPlace());
        return cin;
    }
}
