package com.andrbezr2016.account.management.controller;

import com.andrbezr2016.account.management.dto.AccountFilter;
import com.andrbezr2016.account.management.dto.RequestAccountDto;
import com.andrbezr2016.account.management.dto.ResponseAccountDto;
import com.andrbezr2016.account.management.service.AccountService;
import com.andrbezr2016.account.management.validation.AccountValidator;
import com.andrbezr2016.account.management.validation.AllowedSources;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Validated
@RequestMapping("/api")
@RestController
public class AccountController {

    private final AccountService accountService;
    private final AccountValidator accountValidator;

    @PostMapping("/account")
    public ResponseEntity<?> createAccount(@AllowedSources @RequestHeader(name = "X-Source") String source, @Valid @RequestBody RequestAccountDto requestAccountDto, Errors errors) {
        log.info("Start creating new account");
        accountValidator.validate(source, requestAccountDto, errors);
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
            log.error(message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        log.info("Start creating new account");
        ResponseAccountDto responseAccountDto = accountService.createAccount(requestAccountDto);
        log.info("New account was created");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseAccountDto);
    }

    @GetMapping("/account/{id}")
    public ResponseAccountDto getAccountById(@PathVariable("id") Long id) {
        log.info("Get account by id");
        return accountService.getAccountById(id);
    }

    @PostMapping("/accounts")
    public Collection<ResponseAccountDto> getAccountsByFilter(@Valid @RequestBody AccountFilter accountFilter) {
        log.info("Get accounts by filter");
        return accountService.getAccountsByFilter(accountFilter);
    }
}
