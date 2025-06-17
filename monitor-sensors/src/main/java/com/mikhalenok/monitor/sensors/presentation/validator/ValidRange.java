package com.mikhalenok.monitor.sensors.presentation.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RangeValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRange {
    String message() default "'to' value must be greater than 'from'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
