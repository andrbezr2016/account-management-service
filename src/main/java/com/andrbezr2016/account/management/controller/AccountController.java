package com.andrbezr2016.account.management.controller;

import com.andrbezr2016.account.management.dto.AccountFilter;
import com.andrbezr2016.account.management.dto.RequestAccountDto;
import com.andrbezr2016.account.management.dto.ResponseAccountDto;
import com.andrbezr2016.account.management.service.AccountService;
import com.andrbezr2016.account.management.validation.AccountValidator;
import com.andrbezr2016.account.management.validation.AllowedSources;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Validated
@RequestMapping("/api")
@RestController
public class AccountController {

    private final AccountService accountService;
    private final AccountValidator accountValidator;

    @PostMapping("/account")
    public ResponseEntity<?> createAccount(@AllowedSources @RequestHeader(name = "X-Source") String source, @Valid @RequestBody RequestAccountDto requestAccountDto, Errors errors) {
        accountValidator.validate(source, requestAccountDto, errors);
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", ")));
        }
        ResponseAccountDto responseAccountDto = accountService.createAccount(requestAccountDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseAccountDto);
    }

    @GetMapping("/account/{id}")
    public ResponseAccountDto getAccountById(@PathVariable("id") Long id) {
        return accountService.getAccountById(id);
    }

    @GetMapping("/accounts")
    public Collection<ResponseAccountDto> getAccountsByFilter(@Valid @RequestBody AccountFilter accountFilter) {
        return accountService.getAccountsByFilter(accountFilter);
    }
}
