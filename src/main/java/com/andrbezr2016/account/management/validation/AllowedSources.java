package com.andrbezr2016.account.management.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AllowedSourceConstrainValidator.class)
public @interface AllowedSources {

    String message() default "Wrong X-Source value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
