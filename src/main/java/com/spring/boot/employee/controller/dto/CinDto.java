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
public class CinDto {
    private Integer id;

    private String cinNumber;

    private Date cinDeliveryDate;

    private String cinDeliveryPlace;
}
