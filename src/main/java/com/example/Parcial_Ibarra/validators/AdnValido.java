package com.example.Parcial_Ibarra.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = verificadorADN.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AdnValido {
    String message() default "Secuencia de ADN no valida";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
