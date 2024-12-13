package com.andrbezr2016.account.management.config;

import com.andrbezr2016.account.management.service.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.mock;

@Configuration
@Profile("mocks")
public class MockConfig {

    @Bean
    @Primary
    public AccountService AccountServiceMock() {
        return mock(AccountService.class);
    }
}
