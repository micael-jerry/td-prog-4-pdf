package com.spring.boot.controller.mapper;

import com.spring.boot.model.company.Company;
import com.spring.boot.controller.dto.company.CompanyDto;
import com.spring.boot.controller.dto.company.CreateOrUpdateCompanyDto;
import com.spring.boot.model.company.CompanyAddress;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public Company toEntity(CreateOrUpdateCompanyDto createCompanyDto) {
        Company company = new Company();
        company.setName(createCompanyDto.getName());
        company.setDescription(createCompanyDto.getDescription());
        company.setSlogan(createCompanyDto.getSlogan());
        company.setAddress(new CompanyAddress(
                createCompanyDto.getAddressHouse(),
                createCompanyDto.getAddressStreet(),
                createCompanyDto.getAddressCity(),
                createCompanyDto.getAddressZipCode()));
        company.setEmail(createCompanyDto.getEmail());
        return company;
    }

    public Company toEntity(CompanyDto companyDto) {
        Company company = new Company();
        company.setName(companyDto.getName());
        company.setDescription(companyDto.getDescription());
        company.setSlogan(companyDto.getSlogan());
        company.setAddress(companyDto.getAddress());
        company.setEmail(company.getEmail());
        return company;
    }

    public CompanyDto fromEntity(Company company) {
        return CompanyDto.builder()
                .name(company.getName())
                .description(company.getDescription())
                .slogan(company.getSlogan())
                .address(company.getAddress())
                .email(company.getEmail())
                .build();
    }

    public CreateOrUpdateCompanyDto fromEntityToUpdate(Company company) {
        return CreateOrUpdateCompanyDto.builder()
                .name(company.getName())
                .description(company.getDescription())
                .slogan(company.getSlogan())
                .addressHouse(company.getAddress().getHouse())
                .addressStreet(company.getAddress().getStreet())
                .addressCity(company.getAddress().getCity())
                .addressZipCode(company.getAddress().getZipCode())
                .email(company.getEmail())
                .build();
    }
}
