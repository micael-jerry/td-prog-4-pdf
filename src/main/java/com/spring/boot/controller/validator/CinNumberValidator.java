package com.spring.boot.controller.validator;

import com.spring.boot.repository.CinRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CinNumberValidator implements ConstraintValidator<CinNumberConstraint, String> {
    private CinRepository cinRepository;

    @Override
    public void initialize(CinNumberConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return cinRepository.getByCinNumber(value) == null;
    }
}
