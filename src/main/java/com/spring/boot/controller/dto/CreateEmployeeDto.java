package com.spring.boot.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private LocalDate birthday;

    @NotNull(message = "The sex is mandatory")
    @NotBlank(message = "The sex is mandatory")
    @Size(min = 1, max = 1, message = "Te sex is M or F")
    private String sex;
}
