package com.spring.boot.controller.mapper;

import com.spring.boot.controller.dto.AddressDto;
import com.spring.boot.controller.dto.CreateEmployeeDto;
import com.spring.boot.controller.dto.UpdateEmployeeDto;
import com.spring.boot.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public Address toEntity(CreateEmployeeDto createEmployeeDto) {
        Address address = new Address();
        address.setHouse(createEmployeeDto.getAddressHouse());
        address.setStreet(createEmployeeDto.getAddressStreet());
        address.setCity(createEmployeeDto.getAddressCity());
        address.setZipCode(Integer.valueOf(createEmployeeDto.getAddressZipCode()));
        return address;
    }

    public Address toEntity(UpdateEmployeeDto updateEmployeeDto) {
        Address address = new Address();
        address.setId(updateEmployeeDto.getAddressId());
        address.setHouse(updateEmployeeDto.getAddressHouse());
        address.setStreet(updateEmployeeDto.getAddressStreet());
        address.setCity(updateEmployeeDto.getAddressCity());
        address.setZipCode(Integer.valueOf(updateEmployeeDto.getAddressZipCode()));
        return address;
    }

    public AddressDto fromEntity(Address address) {
        return AddressDto.builder()
                .id(address.getId())
                .house(address.getHouse())
                .street(address.getStreet())
                .city(address.getCity())
                .zipCode(address.getZipCode())
                .build();
    }
}
