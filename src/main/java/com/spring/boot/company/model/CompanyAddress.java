package com.spring.boot.company.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CompanyAddress {
    private String house;

    private String street;

    private String city;

    private String zipCode;
}
