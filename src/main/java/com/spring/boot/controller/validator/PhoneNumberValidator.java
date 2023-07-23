package com.spring.boot.controller.validator;

import com.spring.boot.repository.PhoneRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint, String> {
    private PhoneRepository phoneRepository;

    @Override
    public void initialize(PhoneNumberConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<String> phonesList = this.getPhonesNumber(value);
        for (String phone : phonesList) {
            if (phoneRepository.getByNumber(phone) != null) {
                return false;
            }
        }
        return true;
    }

    private List<String> getPhonesNumber(String phones) {
        String[] phoneArray = phones.replaceAll("\\s+", "").split(",");
        return List.of(phoneArray);
    }
}
