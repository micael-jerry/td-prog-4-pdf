package com.spring.boot.employee.service;

import com.spring.boot.employee.model.Employee;
import com.spring.boot.employee.model.Phone;
import com.spring.boot.employee.repository.PhoneRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PhoneService {
    private PhoneRepository phoneRepository;

    @Transactional
    public List<Phone> saveAll(List<Phone> phones, Employee employee) {
        phones.forEach(phone -> {
            phone.setEmployee(employee);
        });
        return phoneRepository.saveAll(phones);
    }
}
