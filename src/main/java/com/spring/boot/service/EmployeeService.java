package com.spring.boot.service;

import com.spring.boot.model.Employee;
import com.spring.boot.model.Image;
import com.spring.boot.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private ImageService imageService;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee save(Employee employee, MultipartFile image) throws IOException {
        Image imageSaved = imageService.save(image);
        employee.setId_image(imageSaved.getId());
        return employeeRepository.save(employee);
    }

    public Optional<Employee> findById(Integer id) {
        return employeeRepository.findById(id);
    }
}
