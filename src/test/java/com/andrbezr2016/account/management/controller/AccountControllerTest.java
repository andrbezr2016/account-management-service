package com.andrbezr2016.account.management.controller;

import com.andrbezr2016.account.management.AbstractTest;
import com.andrbezr2016.account.management.config.SourcesConfig;
import com.andrbezr2016.account.management.dto.AccountFilter;
import com.andrbezr2016.account.management.dto.RequestAccountDto;
import com.andrbezr2016.account.management.dto.ResponseAccountDto;
import com.andrbezr2016.account.management.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = {"accountServiceMock"})
public class AccountControllerTest extends AbstractTest {

    private static final String CREATE_ACCOUNT_PATH = "/api/account";
    private static final String GET_ACCOUNT_PATH = "/api/account/";
    private static final String GET_ACCOUNTS_PATH = "/api/accounts";

    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    AccountService accountService;
    @Autowired
    SourcesConfig sourcesConfig;

    @Test
    void checkSources() {
        assertEquals(3, sourcesConfig.getSources().size());
        assertEquals(List.of("firstName", "email"), sourcesConfig.getSources().get("mail"));
        assertEquals(List.of("phoneNumber"), sourcesConfig.getSources().get("mobile"));
        assertEquals(List.of("bankId", "lastName", "firstName", "middleName", "birthDate", "passportNumber"), sourcesConfig.getSources().get("bank"));
    }

    @Test
    void createAccountTest() throws Exception {
        String phoneNumber = "70007770077";
        RequestAccountDto requestAccountDto = new RequestAccountDto();
        requestAccountDto.setPhoneNumber(phoneNumber);
        ResponseAccountDto responseAccountDto = new ResponseAccountDto();
        responseAccountDto.setPhoneNumber(phoneNumber);

        doReturn(responseAccountDto).when(accountService).createAccount(eq(requestAccountDto));

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "mobile").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(responseAccountDto)));
    }

    @Test
    void createAccountWithoutXSourceHeaderTest() throws Exception {
        String phoneNumber = "70007770077";
        RequestAccountDto requestAccountDto = new RequestAccountDto();
        requestAccountDto.setPhoneNumber(phoneNumber);

        mvc.perform(post(CREATE_ACCOUNT_PATH).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createAccountWithWrongXSourceHeaderTest() throws Exception {
        String phoneNumber = "70007770077";
        RequestAccountDto requestAccountDto = new RequestAccountDto();
        requestAccountDto.setPhoneNumber(phoneNumber);

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "post-office").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createAccountWithEmptyBodyTest() throws Exception {
        RequestAccountDto requestAccountDto = new RequestAccountDto();

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "mobile").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createAccountWitNullBodyTest() throws Exception {
        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "mobile").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createAccountWithPhoneNumberTest() throws Exception {
        RequestAccountDto requestAccountDto = new RequestAccountDto();
        requestAccountDto.setPhoneNumber("70007770077");

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "mobile").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isCreated());

        requestAccountDto.setPhoneNumber(null);

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "mobile").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isBadRequest());

        requestAccountDto.setPhoneNumber("7000777007");

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "mobile").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isBadRequest());

        requestAccountDto.setPhoneNumber("700077700777");

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "mobile").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isBadRequest());

        requestAccountDto.setPhoneNumber("90007770077");

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "mobile").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isBadRequest());

        requestAccountDto.setPhoneNumber("700a7770077");

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "mobile").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isBadRequest());

        requestAccountDto.setPhoneNumber("7 000 777 00 77");

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "mobile").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isBadRequest());

        requestAccountDto.setPhoneNumber("7(000)777-00-77");

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "mobile").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isBadRequest());

        requestAccountDto.setPhoneNumber("+70007770077");

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "mobile").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createAccountWithPassportNumberTest() throws Exception {
        RequestAccountDto requestAccountDto = new RequestAccountDto();
        requestAccountDto.setBankId(1000L);
        requestAccountDto.setLastName("Кузнецов");
        requestAccountDto.setFirstName("Василий");
        requestAccountDto.setMiddleName("Михайлович");
        requestAccountDto.setBirthDate(LocalDate.EPOCH);
        requestAccountDto.setPassportNumber("7000 777777");

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "bank").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isCreated());

        requestAccountDto.setPassportNumber(null);

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "bank").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isBadRequest());

        requestAccountDto.setPassportNumber("7000777777");

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "bank").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isBadRequest());

        requestAccountDto.setPassportNumber("7000  777777");

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "bank").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isBadRequest());

        requestAccountDto.setPassportNumber(" 7000 777777 ");

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "bank").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isBadRequest());

        requestAccountDto.setPassportNumber("7a00 777777");

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "bank").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isBadRequest());

        requestAccountDto.setPassportNumber("7000 77s777");

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "bank").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isBadRequest());

        requestAccountDto.setPassportNumber("70000777777");

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "bank").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isBadRequest());

        requestAccountDto.setPassportNumber("7000 77777");

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "bank").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isBadRequest());

        requestAccountDto.setPassportNumber("700 77777");

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "bank").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createAccountWithEmailTest() throws Exception {
        RequestAccountDto requestAccountDto = new RequestAccountDto();
        requestAccountDto.setFirstName("Василий");
        requestAccountDto.setEmail("vasil@mail.com");

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "mail").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isCreated());

        requestAccountDto.setEmail(null);

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "mail").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isBadRequest());

        requestAccountDto.setEmail("vasilmail.com");

        mvc.perform(post(CREATE_ACCOUNT_PATH).header("X-Source", "mail").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestAccountDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAccountByIdTest() throws Exception {
        Long id = 12L;
        ResponseAccountDto responseAccountDto = new ResponseAccountDto();
        responseAccountDto.setId(id);
        responseAccountDto.setFirstName("Константин");

        doReturn(responseAccountDto).when(accountService).getAccountById(eq(id));

        mvc.perform(get(GET_ACCOUNT_PATH + id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseAccountDto)));
    }

    @Test
    void getAccountsByFilterTest() throws Exception {
        String firstName = "Виктор";
        String email = "victor32@mail.com";
        AccountFilter accountFilter = new AccountFilter();
        accountFilter.setFirstName(firstName);
        accountFilter.setEmail(email);
        ResponseAccountDto responseAccountDto = new ResponseAccountDto();
        responseAccountDto.setFirstName(firstName);
        responseAccountDto.setEmail(email);
        List<ResponseAccountDto> responseAccountDtoList = List.of(responseAccountDto);

        doReturn(responseAccountDtoList).when(accountService).getAccountsByFilter(eq(accountFilter));

        mvc.perform(get(GET_ACCOUNTS_PATH).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(accountFilter)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseAccountDtoList)));
    }

    @Test
    void getAccountsByFilterWithEmptyFilterTest() throws Exception {
        AccountFilter accountFilter = new AccountFilter();

        doReturn(Collections.emptyList()).when(accountService).getAccountsByFilter(eq(accountFilter));

        mvc.perform(get(GET_ACCOUNTS_PATH).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(accountFilter)))
                .andExpect(status().isBadRequest());
    }
}
