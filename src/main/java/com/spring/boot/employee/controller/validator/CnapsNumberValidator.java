package com.spring.boot.employee.controller.validator;

import com.spring.boot.cnaps.repository.CnapsEmployeeJpaRepository;
import com.spring.boot.repository.Repository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CnapsNumberValidator implements ConstraintValidator<CnapsNumberConstraint, String> {
    private Repository repository;
    private CnapsEmployeeJpaRepository cnapsEmployeeJpaRepository;

    @Override
    public void initialize(CnapsNumberConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return (cnapsEmployeeJpaRepository.getByCnapsNumber(value) != null) && (repository.getByCnapsNumber(value) == null);
    }
}
