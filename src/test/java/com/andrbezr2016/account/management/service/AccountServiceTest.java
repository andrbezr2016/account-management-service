package com.andrbezr2016.account.management.service;

import com.andrbezr2016.account.management.dto.AccountFilter;
import com.andrbezr2016.account.management.dto.RequestAccountDto;
import com.andrbezr2016.account.management.dto.ResponseAccountDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Test
    void createAccountTest() {
        RequestAccountDto requestAccountDto = new RequestAccountDto();
        requestAccountDto.setFirstName("Дмитрий");
        requestAccountDto.setLastName("Новиков");
        requestAccountDto.setPhoneNumber("79008009009");
        ResponseAccountDto responseAccountDto = accountService.createAccount(requestAccountDto);

        assertEquals(requestAccountDto.getFirstName(), responseAccountDto.getFirstName());
        assertEquals(requestAccountDto.getLastName(), responseAccountDto.getLastName());
        assertEquals(requestAccountDto.getPhoneNumber(), responseAccountDto.getPhoneNumber());
    }

    @Test
    void getAccountByIdForExistingAccountTest() {
        ResponseAccountDto responseAccountDto = accountService.getAccountById(4L);

        assertEquals("Елена", responseAccountDto.getFirstName());
        assertEquals("Соколова", responseAccountDto.getLastName());
        assertEquals("70002003030", responseAccountDto.getPhoneNumber());
    }

    @Test
    void getAccountByIdForNonExistingAccountTest() {
        ResponseAccountDto responseAccountDto = accountService.getAccountById(1000L);

        assertNull(responseAccountDto);
    }

    @Test
    void getAccountsByFilterWithOneParameterTest() {
        AccountFilter accountFilter = new AccountFilter();
        accountFilter.setMiddleName("Иванович");

        Collection<ResponseAccountDto> responseAccountDtoCollection = accountService.getAccountsByFilter(accountFilter);

        assertNotNull(responseAccountDtoCollection);
        assertEquals(2, responseAccountDtoCollection.size());
        for (ResponseAccountDto responseAccountDto : responseAccountDtoCollection) {
            assertEquals("Иванович", responseAccountDto.getMiddleName());
            assertTrue(List.of("Иван", "Егор").contains(responseAccountDto.getFirstName()));
        }
    }

    @Test
    void getAccountsByFilterWithMultipleParametersTest() {
        AccountFilter accountFilter = new AccountFilter();
        accountFilter.setMiddleName("Иванович");
        accountFilter.setEmail("egor@mail.com");

        Collection<ResponseAccountDto> responseAccountDtoCollection = accountService.getAccountsByFilter(accountFilter);

        assertNotNull(responseAccountDtoCollection);
        assertEquals(1, responseAccountDtoCollection.size());
        assertEquals("Егор", responseAccountDtoCollection.iterator().next().getFirstName());
        assertEquals("Иванович", responseAccountDtoCollection.iterator().next().getMiddleName());
        assertEquals("egor@mail.com", responseAccountDtoCollection.iterator().next().getEmail());
    }

    @Test
    void getAccountsByFilterWithEmptyParametersTest() {
        AccountFilter accountFilter = new AccountFilter();

        Collection<ResponseAccountDto> responseAccountDtoCollection = accountService.getAccountsByFilter(accountFilter);

        assertNotNull(responseAccountDtoCollection);
        assertEquals(0, responseAccountDtoCollection.size());
    }
}
