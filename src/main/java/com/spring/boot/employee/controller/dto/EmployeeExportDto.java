package com.spring.boot.employee.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeExportDto {
    private String personnelNumber;

    private String firstname;

    private String lastname;

    private int age;

    private Integer id_image;

    private String cnapsNumber;

    private Date startDate;

    private Date departureDate;

    private EmailDto professionalEmail;
}
