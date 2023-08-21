package com.spring.boot.employee.controller.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = CnapsNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CnapsNumberConstraint {
    String message() default "The Cnaps number is already in use by another employee OR not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
