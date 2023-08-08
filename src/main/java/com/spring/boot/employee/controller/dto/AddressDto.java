package com.spring.boot.employee.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Integer id;

    private String house;

    private String street;

    private String city;

    private Integer zipCode;
}
