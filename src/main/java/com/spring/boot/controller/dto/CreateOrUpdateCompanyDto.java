package com.spring.boot.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
}
