package com.spring.boot.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeDto {
    private String firstname;

    private String lastname;

    private Date birthday;
}
