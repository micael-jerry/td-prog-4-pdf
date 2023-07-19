package com.spring.boot.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeDto {
    private Integer id;

    private String firstname;

    private String lastname;

    private LocalDate birthday;

    private Integer id_image;

    private String personnelNumber;
}
