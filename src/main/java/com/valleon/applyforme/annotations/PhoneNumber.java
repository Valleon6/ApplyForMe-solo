package com.valleon.applyforme.annotations;

import com.valleon.applyforme.validators.PhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {

    String message() default "Phone number length should be between 10 and 15 characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default{};
}
