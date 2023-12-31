package com.spring.boot.company.controller.dto;

import com.spring.boot.company.model.CompanyAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private String name;

    private String description;

    private String slogan;

    private String fiscalIdentityNif;

    private String fiscalIdentityStat;

    private String fiscalIdentityRcs;

    private CompanyAddress address;

    private String email;

    private List<String> phones;
}
