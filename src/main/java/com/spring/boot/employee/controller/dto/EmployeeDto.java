package com.spring.boot.employee.controller.dto;

import com.spring.boot.employee.model.Sex;
import com.spring.boot.employee.model.SocioProfessionalCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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

    private Long salary;

    private Integer childrenCount;

    private SocioProfessionalCategory socioProfessionalCategory;

    private String function;

    private Date startDate;

    private Date departureDate;

    private Integer id_image;

    private AddressDto address;

    private CinDto cin;

    private EmailDto personalEmail;

    private EmailDto professionalEmail;

    private List<PhoneDto> phones;
}
