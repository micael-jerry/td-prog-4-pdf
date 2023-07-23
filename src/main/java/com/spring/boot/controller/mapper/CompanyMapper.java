package com.spring.boot.controller.mapper;

import com.spring.boot.model.Company;
import com.spring.boot.controller.dto.CompanyDto;
import com.spring.boot.controller.dto.CreateOrUpdateCompanyDto;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public Company toEntity(CreateOrUpdateCompanyDto createCompanyDto) {
        Company company = new Company();
        company.setName(createCompanyDto.getName());
        company.setDescription(createCompanyDto.getDescription());
        company.setSlogan(createCompanyDto.getSlogan());
        return company;
    }

    public Company toEntity(CompanyDto companyDto) {
        Company company = new Company();
        company.setName(companyDto.getName());
        company.setDescription(companyDto.getDescription());
        company.setSlogan(companyDto.getSlogan());
        return company;
    }

    public CompanyDto fromEntity(Company company) {
        return CompanyDto.builder()
                .name(company.getName())
                .description(company.getDescription())
                .slogan(company.getSlogan())
                .build();
    }

    public CreateOrUpdateCompanyDto fromEntityToUpdate(Company company) {
        return CreateOrUpdateCompanyDto.builder()
                .name(company.getName())
                .description(company.getDescription())
                .slogan(company.getSlogan())
                .build();
    }
}
