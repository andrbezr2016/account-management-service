package com.andrbezr2016.account.management.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class AccountDto {

    private Long id;
    private Long bankId;
    private String lastName;
    private String firstName;
    private String middleName;
    private OffsetDateTime birthDate;
    private String birthPlace;
    private String passportNumber;
    private String phoneNumber;
    private String email;
    private String registrationAddress;
    private String residenceAddress;
}
