package com.spring.boot.controller.dto;

import com.spring.boot.controller.validator.CinNumberConstraint;
import com.spring.boot.controller.validator.CnapsNumberConstraint;
import com.spring.boot.controller.validator.EmailConstraint;
import com.spring.boot.controller.validator.PhoneNumberConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeDto {
    @Size(max = 100, message = "The first name is too long")
    private String firstname;

    @NotBlank(message = "The last name is mandatory")
    @NotNull(message = "The last name is mandatory")
    @Size(max = 100, message = "The last name is too long")
    private String lastname;

    @NotNull(message = "The birthday is mandatory")
    @NotBlank(message = "The birthday is mandatory")
    private String birthday;

    @NotNull(message = "The sex is mandatory")
    @NotBlank(message = "The sex is mandatory")
    @Size(min = 1, max = 1, message = "Te sex is M or F")
    private String sex;

    @CnapsNumberConstraint
    private String cnapsNumber;

    private Integer childrenCount;

    @NotNull(message = "The socio-professional category is mandatory")
    @NotBlank(message = "The socio-professional category is mandatory")
    private String socioProfessionalCategory;

    @NotNull(message = "Function is mandatory")
    @NotBlank(message = "Function is mandatory")
    private String function;

    @NotNull(message = "start date is mandatory")
    @NotBlank(message = "start date is mandatory")
    private String startDate;

    //    ----------------- Address Information --------------------
    private String addressHouse;

    private String addressStreet;

    @NotNull(message = "The city is mandatory")
    @NotBlank(message = "The city is mandatory")
    private String addressCity;

    @NotNull(message = "The zipCode is mandatory")
    @NotBlank(message = "The zipCode is mandatory")
    @Pattern(regexp = "[0-9]+", message = "The zipCode must contain only numbers")
    private String addressZipCode;
    //    --------------- Address Information ---------------------

    //  ---------------  CIN Information ----------------
    @NotNull(message = "The cin number is mandatory")
    @NotBlank(message = "The cin number is mandatory")
    @Pattern(regexp = "[0-9]+", message = "The cin number must contain only numbers")
    @CinNumberConstraint
    private String cinNumber;

    @NotNull(message = "The cin delivery date is mandatory")
    @NotBlank(message = "The cin delivery date is mandatory")
    private String cinDeliveryDate;

    @NotNull(message = "The cin delivery place is mandatory")
    @NotBlank(message = "The cin delivery place is mandatory")
    private String cinDeliveryPlace;
    //  ---------------  CIN Information ----------------

    @Email(message = "Personal email is not well formed")
    @EmailConstraint(message = "personal email already exist")
    private String personalEmail;

    @Email(message = "Personal email is not well formed")
    @NotNull(message = "Professional email is mandatory")
    @NotBlank(message = "Professional email is mandatory")
    @EmailConstraint(message = "professional email already exist")
    private String professionalEmail;

    @Pattern(regexp = "^[0-9+,-]+$", message = "phones not well formed")
    @PhoneNumberConstraint
    private String phones;
}
