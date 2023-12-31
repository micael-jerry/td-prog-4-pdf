package com.spring.boot.company.controller;

import com.spring.boot.company.controller.dto.CompanyDto;
import com.spring.boot.company.controller.dto.CreateOrUpdateCompanyDto;
import com.spring.boot.company.controller.mapper.CompanyMapper;
import com.spring.boot.company.model.Company;
import com.spring.boot.company.service.CompanyService;
import com.spring.boot.employee.service.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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

import static com.spring.boot.employee.utils.ModelAttributeName.COMPANY_ATTRIBUTE;
import static com.spring.boot.employee.utils.ModelAttributeName.CREATE_COMPANY_ATTRIBUTE;
import static com.spring.boot.employee.utils.ModelAttributeName.UPDATE_COMPANY_ATTRIBUTE;

@Controller
@AllArgsConstructor
public class CompanyController {
    private CompanyService companyService;
    private CompanyMapper companyMapper;
    private LoginService loginService;

    @GetMapping("/company")
    public String getCompany(HttpSession session, Model model) {
        if (!loginService.isAuthenticated(session)) {
            return "redirect:/login";
        }
        CompanyDto companyDto = companyMapper.fromEntity(companyService.get());
        model.addAttribute(COMPANY_ATTRIBUTE, companyDto);
        return "viewCompany";
    }

    @GetMapping("/save-company")
    public String getSaveCompanyPage(HttpSession session, Model model) {
        if (!loginService.isAuthenticated(session)) {
            return "redirect:/login";
        }
        model.addAttribute(CREATE_COMPANY_ATTRIBUTE, new CreateOrUpdateCompanyDto());
        return "saveCompany";
    }

    @PostMapping("/company")
    public String saveCompany(
            HttpSession session,
            @Valid @ModelAttribute("createCompany") CreateOrUpdateCompanyDto createCompanyDto,
            BindingResult result,
            @RequestParam("logo") MultipartFile logo
    ) throws IOException {
        if (!loginService.isAuthenticated(session)) {
            return "redirect:/login";
        }
        if (result.hasErrors()) {
            return "saveCompany";
        }
        companyService.save(
                companyMapper.toEntity(createCompanyDto),
                logo.getBytes(),
                false
        );
        return "redirect:/company";
    }

    @GetMapping("/update-company")
    public String getUpdateCompanyPage(HttpSession session, Model model) {
        if (!loginService.isAuthenticated(session)) {
            return "redirect:/login";
        }
        Company company = companyService.get();
        if (company.getName() == null) {
            return "redirect:/save-company";
        }
        CreateOrUpdateCompanyDto updateCompanyDto = companyMapper.fromEntityToUpdate(company);
        model.addAttribute(UPDATE_COMPANY_ATTRIBUTE, updateCompanyDto);
        return "updateCompany";
    }

    @PostMapping("/update-company")
    public String updateCompany(
            HttpSession session,
            @ModelAttribute("updateCompany") CreateOrUpdateCompanyDto updateCompanyDto,
            @RequestParam("logo") MultipartFile logo
    ) throws IOException {
        if (!loginService.isAuthenticated(session)) {
            return "redirect:/login";
        }
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
