package com.spring.boot.employee.controller.validator;

import com.spring.boot.employee.repository.PhoneRepository;
import com.spring.boot.employee.utils.PhoneUtil;
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
    public boolean isValid(String phones, ConstraintValidatorContext context) {
        List<String[]> phonesList = PhoneUtil.getCountryCodeAndNumber(phones);
        if (phonesList.stream().distinct().count() != phonesList.size()) {
            return false;
        }
        for (String[] phone : phonesList) {
            if (phoneRepository.existsByCountryCodeAndNumber(phone[0], phone[1]) || phone[1].length() != 9) {
                return false;
            }
        }
        return true;
    }
}
