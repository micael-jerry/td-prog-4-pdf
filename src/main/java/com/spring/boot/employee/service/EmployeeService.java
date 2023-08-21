package com.spring.boot.employee.service;

import com.spring.boot.employee.model.Cin;
import com.spring.boot.employee.model.Email;
import com.spring.boot.employee.model.Employee;
import com.spring.boot.employee.model.Image;
import com.spring.boot.employee.model.Phone;
import com.spring.boot.repository.Repository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeeService {
    private Repository repository;
    private ImageService imageService;
    private CinService cinService;
    private EmailService emailService;
    private PhoneService phoneService;
    private AddressService addressService;

    public List<Employee> findAll(
            String function, String lastname, String firstname, String sex, String countryCode, String startDate, String departureDate, String orderBy, String direction
    ) {
        if (countryCode.length() > 1) {
            return this.findByCountryCode(countryCode);
        }
        if (orderBy.length() > 1) {
            return this.findAllByCriteriaWithSort(function, lastname, firstname, sex, startDate, departureDate, orderBy, direction);
        } else {
            return this.findAllByCriteria(function, lastname, firstname, sex, startDate, departureDate);
        }
    }

    public List<Employee> findByCountryCode(String countryCode) {
        return repository.findAllByCountryCode(countryCode);
    }

    @Transactional
    public Employee save(Employee employee, MultipartFile image) throws IOException {
//        Save image
        Image imageSaved = imageService.save(image);
        employee.setId_image(imageSaved.getId());
//        Save cin
        Cin cinSaved = cinService.save(employee.getCin());
        employee.setCin(cinSaved);
//        Save Email
        Email personal = emailService.save(employee.getPersonalEmail());
        employee.setPersonalEmail(personal);
        Email professional = emailService.save(employee.getProfessionalEmail());
        employee.setProfessionalEmail(professional);

        List<Phone> phones = employee.getPhones();
        Employee employeeSaved = repository.save(employee);
        employeeSaved.setPhones(null);
        phoneService.saveAll(phones, employeeSaved);
        return employeeSaved;
    }

    public Employee update(Employee employee, MultipartFile image) throws IOException {
//        Image update
        Image imageUpdated = imageService.update(employee.getId_image(), image);
        employee.setId_image(imageUpdated.getId());
//        Cin update
        cinService.update(employee.getCin());
//        Email update
        emailService.update(employee.getPersonalEmail());
        emailService.update(employee.getProfessionalEmail());
//        Address update
        addressService.update(employee.getAddress());

        return repository.save(employee);
    }

    public Optional<Employee> findById(Integer id) {
        return repository.findById(id);
    }

    private List<Employee> findAllByCriteria(
            String function, String lastname, String firstname, String sex, String startDate, String departureDate
    ) {
        if (this.isValidDate(startDate) && this.isValidDate(departureDate)) {
            return repository
                    .findAllByCriteriaBetweenStartAndDeparture(function, lastname, firstname, sex, startDate, departureDate);
        } else if (this.isValidDate(startDate) && !this.isValidDate(departureDate)) {
            return repository
                    .findAllByCriteriaAfterStart(function, lastname, firstname, sex, startDate);
        } else if (!this.isValidDate(startDate) && this.isValidDate(departureDate)) {
            return repository
                    .findAllByCriteriaBeforeDeparture(function, lastname, firstname, sex, departureDate);
        }
        return repository
                .findAllByCriteria(function, lastname, firstname, sex);
    }

    private List<Employee> findAllByCriteriaWithSort(
            String function, String lastname, String firstname, String sex, String startDate, String departureDate, String orderBy, String direction) {
        if (this.isValidDate(startDate) && this.isValidDate(departureDate)) {
            return repository
                    .findAllByCriteriaBetweenStartAndDepartureWithSort(function, lastname, firstname, sex, startDate, departureDate, orderBy, direction);
        } else if (this.isValidDate(startDate) && !this.isValidDate(departureDate)) {
            return repository
                    .findAllByCriteriaAfterStartWithSort(function, lastname, firstname, sex, startDate, orderBy, direction);
        } else if (!this.isValidDate(startDate) && this.isValidDate(departureDate)) {
            return repository
                    .findAllByCriteriaBeforeDepartureWithSort(function, lastname, firstname, sex, departureDate, orderBy, direction);
        }
        return repository
                .findAllByCriteriaWithSort(function, lastname, firstname, sex, orderBy, direction);
    }

    private boolean isValidDate(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            simpleDateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            log.error("PARSE DATE ERROR: " + date);
            return false;
        }
    }

    public String exportUrlParams(
            String function, String lastname, String firstname, String sex, String countryCode, String startDate, String departureDate, String orderBy, String direction
    ) {
        String params = "?firstname_filter=" + firstname.replaceAll(" ", "+") +
                "&lastname_filter=" + lastname.replaceAll(" ", "+") +
                "&function_filter=" + function.replaceAll(" ", "+") +
                "&country_code_filter=" + countryCode.replaceAll(" ", "+") +
                "&sex_filter=" + sex +
                "&country_code_filter=" + countryCode +
                "&start_date=" + startDate +
                "&departure_date=" + departureDate +
                "&order_by=" + orderBy +
                "&order_direction=" + direction;
        log.info("EXPORT PARAMS => " + params);
        return params;
    }
}
