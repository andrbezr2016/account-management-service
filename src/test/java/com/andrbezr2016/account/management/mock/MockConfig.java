package com.andrbezr2016.account.management.mock;

import com.andrbezr2016.account.management.service.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.mock;

@Configuration
public class MockConfig {

    @Bean
    @Primary
    @Profile("accountServiceMock")
    public AccountService accountServiceMock() {
        return mock(AccountService.class);
    }
}
