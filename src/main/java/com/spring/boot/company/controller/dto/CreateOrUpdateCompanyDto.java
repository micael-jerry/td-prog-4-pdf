package com.spring.boot.company.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateCompanyDto {
    @NotBlank(message = "name is mandatory")
    @NotNull(message = "name is mandatory")
    private String name;

    @NotBlank(message = "description is mandatory")
    @NotNull(message = "description is mandatory")
    private String description;

    @NotBlank(message = "slogan is mandatory")
    @NotNull(message = "slogan is mandatory")
    private String slogan;

    @NotBlank(message = "Nif is mandatory")
    @NotNull(message = "Nif is mandatory")
    private String fiscalIdentityNif;

    @NotBlank(message = "Stat is mandatory")
    @NotNull(message = "Stat is mandatory")
    private String fiscalIdentityStat;

    @NotBlank(message = "Rcs is mandatory")
    @NotNull(message = "Rcs is mandatory")
    private String fiscalIdentityRcs;

    @Email(message = "Personal email is not well formed")
    @NotNull(message = "Professional email is mandatory")
    @NotBlank(message = "Professional email is mandatory")
    private String email;

    //    ----------------- Address Information --------------------
    @NotNull(message = "The house is mandatory")
    @NotBlank(message = "The house is mandatory")
    private String addressHouse;

    @NotNull(message = "The street is mandatory")
    @NotBlank(message = "The street is mandatory")
    private String addressStreet;

    @NotNull(message = "The city is mandatory")
    @NotBlank(message = "The city is mandatory")
    private String addressCity;

    @NotNull(message = "The zipCode is mandatory")
    @NotBlank(message = "The zipCode is mandatory")
    @Pattern(regexp = "[0-9]+", message = "The zipCode must contain only numbers")
    private String addressZipCode;
    //    --------------- Address Information ---------------------

    @Pattern(regexp = "^[0-9+,-]+$", message = "phones not well formed")
    @NotNull(message = "phones is mandatory")
    @NotBlank(message = "phones is mandatory")
    private String phones;
}
