package com.spring.boot.employee.controller.validator;

import com.spring.boot.employee.repository.EmployeeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CnapsNumberValidator implements ConstraintValidator<CnapsNumberConstraint, String> {
    private EmployeeRepository employeeRepository;

    @Override
    public void initialize(CnapsNumberConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return employeeRepository.getByCnapsNumber(value) == null;
    }
}
