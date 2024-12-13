package com.andrbezr2016.account.management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestAccountDto {

    private Long bankId;
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate birthDate;
    private String birthPlace;
    @Pattern(regexp = "^[0-9]{4} [0-9]{6}$", message = "Wrong passport number format")
    private String passportNumber;
    @Pattern(regexp = "^7[0-9]{10}$", message = "Wrong phone number format")
    private String phoneNumber;
    @Email(message = "Wrong email format")
    private String email;
    private String registrationAddress;
    private String residenceAddress;
}
