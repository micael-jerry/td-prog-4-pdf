package com.spring.boot.employee.controller.mapper;

import com.spring.boot.employee.controller.dto.company.CompanyDto;
import com.spring.boot.employee.controller.dto.company.CreateOrUpdateCompanyDto;
import com.spring.boot.employee.model.company.Company;
import com.spring.boot.employee.model.company.CompanyAddress;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyMapper {

    public Company toEntity(CreateOrUpdateCompanyDto createCompanyDto) {
        Company company = new Company();
        company.setName(createCompanyDto.getName());
        company.setDescription(createCompanyDto.getDescription());
        company.setSlogan(createCompanyDto.getSlogan());
        company.setFiscalIdentityNif(createCompanyDto.getFiscalIdentityNif());
        company.setFiscalIdentityStat(createCompanyDto.getFiscalIdentityStat());
        company.setFiscalIdentityRcs(createCompanyDto.getFiscalIdentityRcs());
        company.setAddress(new CompanyAddress(
                createCompanyDto.getAddressHouse(),
                createCompanyDto.getAddressStreet(),
                createCompanyDto.getAddressCity(),
                createCompanyDto.getAddressZipCode()));
        company.setEmail(createCompanyDto.getEmail());
        company.setPhones(this.getPhonesNumberList(createCompanyDto.getPhones()));
        return company;
    }

    public Company toEntity(CompanyDto companyDto) {
        Company company = new Company();
        company.setName(companyDto.getName());
        company.setDescription(companyDto.getDescription());
        company.setSlogan(companyDto.getSlogan());
        company.setFiscalIdentityNif(companyDto.getFiscalIdentityNif());
        company.setFiscalIdentityStat(companyDto.getFiscalIdentityStat());
        company.setFiscalIdentityRcs(companyDto.getFiscalIdentityRcs());
        company.setAddress(companyDto.getAddress());
        company.setEmail(company.getEmail());
        company.setPhones(companyDto.getPhones());
        return company;
    }

    public CompanyDto fromEntity(Company company) {
        return CompanyDto.builder()
                .name(company.getName())
                .description(company.getDescription())
                .slogan(company.getSlogan())
                .fiscalIdentityNif(company.getFiscalIdentityNif())
                .fiscalIdentityStat(company.getFiscalIdentityStat())
                .fiscalIdentityRcs(company.getFiscalIdentityRcs())
                .address(company.getAddress())
                .email(company.getEmail())
                .phones(company.getPhones())
                .build();
    }

    public CreateOrUpdateCompanyDto fromEntityToUpdate(Company company) {
        return CreateOrUpdateCompanyDto.builder()
                .name(company.getName())
                .description(company.getDescription())
                .slogan(company.getSlogan())
                .fiscalIdentityNif(company.getFiscalIdentityNif())
                .fiscalIdentityStat(company.getFiscalIdentityStat())
                .fiscalIdentityRcs(company.getFiscalIdentityRcs())
                .addressHouse(company.getAddress().getHouse())
                .addressStreet(company.getAddress().getStreet())
                .addressCity(company.getAddress().getCity())
                .addressZipCode(company.getAddress().getZipCode())
                .email(company.getEmail())
                .phones(this.getPhoneNumberString(company.getPhones()))
                .build();
    }

    private List<String> getPhonesNumberList(String phones) {
        String[] phoneArray = phones.replaceAll("\\s+", "").split(",");
        return List.of(phoneArray);
    }

    private String getPhoneNumberString(List<String> phones) {
        return String.join(", ", phones);
    }
}
