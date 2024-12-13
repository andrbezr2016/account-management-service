package com.andrbezr2016.account.management.validation;

import com.andrbezr2016.account.management.config.SourcesConfig;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AllowedSourceConstrainValidator implements ConstraintValidator<AllowedSources, String> {

    private final SourcesConfig sourcesConfig;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return sourcesConfig.getSources() == null || sourcesConfig.getSources().containsKey(value);
    }
}
