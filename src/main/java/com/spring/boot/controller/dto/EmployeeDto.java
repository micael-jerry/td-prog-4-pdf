package com.spring.boot.controller.dto;

import com.spring.boot.model.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Integer id;

    private String personnelNumber;

    private String firstname;

    private String lastname;

    private Date birthday;

    private Sex sex;

    private String cnapsNumber;

    private Integer childrenCount;

    private Integer id_image;

    private CinDto cin;
}
