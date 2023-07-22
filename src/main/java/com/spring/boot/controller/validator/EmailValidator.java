package com.spring.boot.controller.validator;

import com.spring.boot.repository.EmailRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {
    private EmailRepository emailRepository;

    @Override
    public void initialize(EmailConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return emailRepository.getByAddress(value) == null;
    }
}
