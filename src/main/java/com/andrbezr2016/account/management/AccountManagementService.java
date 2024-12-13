package com.andrbezr2016.account.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.andrbezr2016.account.management.config")
public class AccountManagementService {

    public static void main(String[] args) {
        SpringApplication.run(AccountManagementService.class, args);
    }
}
