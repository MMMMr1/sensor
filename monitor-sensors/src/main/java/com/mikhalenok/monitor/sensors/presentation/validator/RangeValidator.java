package com.mikhalenok.monitor.sensors.presentation.validator;

import com.mikhalenok.monitor.sensors.presentation.model.sensor.RangeRq;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class RangeValidator implements ConstraintValidator<ValidRange, RangeRq> {
    @Override
    public boolean isValid(RangeRq range, ConstraintValidatorContext context) {
        if(Objects.isNull(range)){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Range must not be null")
                    .addConstraintViolation();
            return false;
        }
        if (range.to() < 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Range 'to' must be positive.")
                    .addConstraintViolation();
            return false;
        }
        if (range.from() < 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Range 'from' must be positive.")
                    .addConstraintViolation();
            return false;
        }

        return range.to() > range.from();
    }
}