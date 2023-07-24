package com.spring.boot.controller.dto.company;

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
}
