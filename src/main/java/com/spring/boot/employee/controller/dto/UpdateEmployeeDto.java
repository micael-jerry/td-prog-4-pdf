package com.spring.boot.employee.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeDto {
    private Integer id;

    private String personnelNumber;

    private String firstname;

    private String lastname;

    private String birthday;

    private String sex;

    private String cnapsNumber;

    private Long salary;

    private Integer childrenCount;

    private String socioProfessionalCategory;

    private String function;

    private String startDate;

    private String departureDate;

    private Integer id_image;

    private Integer addressId;
    private String addressHouse;
    private String addressStreet;
    private String addressCity;
    private String addressZipCode;

    private Integer cinId;
    private String cinNumber;
    private String cinDeliveryDate;
    private String cinDeliveryPlace;

    private Integer personalEmailId;
    private String personalEmail;

    private Integer professionalEmailId;
    private String professionalEmail;
}
