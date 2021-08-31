package com.featurelogs.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WorkableAgeConstraintValidator implements ConstraintValidator<WithinWorkableAge, Integer> {

    int lowest;
    int highest;

    @Override
    public void initialize(WithinWorkableAge constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.highest = constraintAnnotation.max();
        this.lowest = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value > lowest && value < highest;
    }
}
