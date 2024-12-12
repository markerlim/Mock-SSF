package com.assessment.ssf.MOCK_SSF.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateValidator implements ConstraintValidator<ValidDate, LocalDate> {

    @Override
    public void initialize(ValidDate constraintAnnotation) {
        // Initialization logic if required
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Let @NotNull handle null checks
        }

        try {
            // Example: Ensure date is in valid format and in the past
            LocalDate today = LocalDate.now();
            return value.isBefore(today) || value.isEqual(today);
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
