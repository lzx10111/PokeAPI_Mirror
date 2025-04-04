package com.example.PokemonAPI.annotation.validator;

import com.example.PokemonAPI.annotation.DateHtml;
import com.example.PokemonAPI.util.ValidMethods;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<DateHtml, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return ValidMethods.isDateHtml(s);
    }
}
