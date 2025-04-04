package com.example.PokemonAPI.annotation;

import com.example.PokemonAPI.annotation.validator.IntegerPositiveValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IntegerPositiveValidator.class)
public @interface IntegerPositive {
    boolean allowPlusSign() default false;

    boolean allowEmptyValue() default false;

    String message() default "String is not a positive integer";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
