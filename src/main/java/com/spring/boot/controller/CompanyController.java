package com.spring.boot.controller;

import com.spring.boot.service.CompanyService;
import com.spring.boot.controller.dto.CompanyDto;
import com.spring.boot.controller.dto.CreateOrUpdateCompanyDto;
import com.spring.boot.controller.mapper.CompanyMapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.spring.boot.utils.ModelAttributeName.COMPANY_ATTRIBUTE;
import static com.spring.boot.utils.ModelAttributeName.CREATE_COMPANY_ATTRIBUTE;
import static com.spring.boot.utils.ModelAttributeName.UPDATE_COMPANY_ATTRIBUTE;

@Controller
@AllArgsConstructor
public class CompanyController {
    private CompanyService companyService;
    private CompanyMapper companyMapper;

    @GetMapping("/company")
    public String getCompany(Model model) {
        CompanyDto companyDto = companyMapper.fromEntity(companyService.get());
        model.addAttribute(COMPANY_ATTRIBUTE, companyDto);
        return "viewCompany";
    }

    @GetMapping("/save-company")
    public String getSaveCompanyPage(Model model) {
        model.addAttribute(CREATE_COMPANY_ATTRIBUTE, new CreateOrUpdateCompanyDto());
        return "saveCompany";
    }

    @PostMapping("/company")
    public String saveCompany(
            @Valid @ModelAttribute("createCompany") CreateOrUpdateCompanyDto createCompanyDto,
            BindingResult result,
            @RequestParam("logo") MultipartFile logo
    ) throws IOException {
        if (result.hasErrors()) {
            return "saveCompany";
        }
        companyService.save(
                companyMapper.toEntity(createCompanyDto),
                logo.getBytes(),
                false
        );
        return "redirect:/save-company";
    }

    @GetMapping("/update-company")
    public String getUpdateCompanyPage(Model model) {
        CreateOrUpdateCompanyDto updateCompanyDto = companyMapper.fromEntityToUpdate(companyService.get());
        model.addAttribute(UPDATE_COMPANY_ATTRIBUTE, updateCompanyDto);
        return "updateCompany";
    }

    @PostMapping("/company/update")
    public String updateCompany(
            @ModelAttribute("updateCompany") CreateOrUpdateCompanyDto updateCompanyDto,
            @RequestParam("logo") MultipartFile logo
    ) throws IOException {
        companyService.save(companyMapper.toEntity(updateCompanyDto), logo.getBytes(), true);
        return "redirect:/company";
    }

    @GetMapping("/show-logo-company")
    public void showImage(HttpServletResponse response) throws IOException {
        response.setContentType("image/*");
        response.getOutputStream().write(companyService.get().getLogo());
        response.getOutputStream().close();
    }
}
