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
public class LoginDto {
    @NotNull(message = "The username is mandatory")
    @NotBlank(message = "The username is mandatory")
    private String username;

    @NotNull(message = "The birthday is mandatory")
    @NotBlank(message = "The cin delivery date is mandatory")
    private String password;
}
