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
public class UpdateCinDto {
    private String cinNumber;

    private Date cinDeliveryDate;

    private String cinDeliveryPlace;
}
