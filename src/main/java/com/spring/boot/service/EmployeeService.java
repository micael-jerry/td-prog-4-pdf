package com.spring.boot.service;

import com.spring.boot.model.Cin;
import com.spring.boot.model.Email;
import com.spring.boot.model.Employee;
import com.spring.boot.model.Image;
import com.spring.boot.model.Phone;
import com.spring.boot.model.Sex;
import com.spring.boot.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private ImageService imageService;
    private CinService cinService;
    private EmailService emailService;
    private PhoneService phoneService;
    private AddressService addressService;

    public List<Employee> findAll(String function, String lastname, String firstname, String sex, String orderBy, String direction) {
        if (sex.length() == 1) {
            return employeeRepository
                    .findAllByFunctionContainsIgnoreCaseAndLastnameContainsIgnoreCaseAndFirstnameContainsIgnoreCaseAndSex(
                            function, lastname, firstname, Sex.valueOf(sex), this.createSort(orderBy, direction));
        }
        return employeeRepository
                .findAllByFunctionContainsIgnoreCaseAndLastnameContainsIgnoreCaseAndFirstnameContainsIgnoreCase(
                        function, lastname, firstname, this.createSort(orderBy, direction));
    }

    private Sort createSort(String orderBy, String direction) {
        if (!orderBy.isBlank()) {
            if (Objects.equals(direction, "ASC")) {
                return Sort.by(orderBy).ascending();
            } else if (Objects.equals(direction, "DESC")) {
                return Sort.by(orderBy).descending();
            }
        }
        return null;
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
        Employee employeeSaved = employeeRepository.save(employee);
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

        return employeeRepository.save(employee);
    }

    public Optional<Employee> findById(Integer id) {
        return employeeRepository.findById(id);
    }
}
