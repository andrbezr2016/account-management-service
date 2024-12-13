package com.andrbezr2016.account.management.dto;

import jakarta.validation.constraints.AssertTrue;
import lombok.Data;

@Data
public class AccountFilter {

    private String lastName;
    private String firstName;
    private String middleName;
    private String phoneNumber;
    private String email;

    @AssertTrue(message = "All fields are empty")
    private boolean isValidAccountFilter() {
        return lastName != null
                || firstName != null
                || middleName != null
                || phoneNumber != null
                || email != null;
    }
}
