package com.example.PokemonAPI.annotation;

import com.example.PokemonAPI.annotation.validator.DateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
public @interface DateHtml {
    String message() default "Incorrect date format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
