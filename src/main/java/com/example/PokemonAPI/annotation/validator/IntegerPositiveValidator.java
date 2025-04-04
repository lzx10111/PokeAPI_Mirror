package com.example.PokemonAPI.annotation.validator;

import com.example.PokemonAPI.annotation.IntegerPositive;
import com.example.PokemonAPI.util.ValidMethods;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IntegerPositiveValidator implements ConstraintValidator<IntegerPositive, String> {
    private Boolean allowPlusSign;
    private Boolean allowEmptyValue;

    @Override
    public void initialize(IntegerPositive constraintAnnotation) {
        allowPlusSign = constraintAnnotation.allowPlusSign();
        allowEmptyValue = constraintAnnotation.allowEmptyValue();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return ValidMethods.isPositiveInteger(s, allowPlusSign, allowEmptyValue);
    }
}
