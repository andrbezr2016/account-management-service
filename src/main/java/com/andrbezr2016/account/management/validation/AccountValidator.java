package com.andrbezr2016.account.management.validation;

import com.andrbezr2016.account.management.config.SourcesConfig;
import com.andrbezr2016.account.management.dto.RequestAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.lang.reflect.Field;
import java.util.List;

@RequiredArgsConstructor
@Component
public class AccountValidator {

    private final SourcesConfig sourcesConfig;

    public void validate(String source, RequestAccountDto requestAccountDto, Errors errors) {
        if (sourcesConfig.getSources() == null) {
            return;
        }
        List<String> fieldsToValid = sourcesConfig.getSources().get(source);
        Field[] declaredFields = requestAccountDto.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            String fieldName = field.getName();
            if (fieldsToValid.contains(fieldName)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(requestAccountDto);
                    if (value == null) {
                        errors.reject(fieldName, String.format("Field %s is required", fieldName));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
