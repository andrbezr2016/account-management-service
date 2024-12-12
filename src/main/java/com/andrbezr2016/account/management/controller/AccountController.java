package com.andrbezr2016.account.management.controller;

import com.andrbezr2016.account.management.dto.AccountDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/api")
@RestController
public class AccountController {

    @PostMapping("/account")
    public void createAccount(@Valid @RequestBody AccountDto accountDto) {
    }

    @GetMapping("/account")
    public AccountDto getAccountById() {
        return null;
    }

    @GetMapping("/accounts")
    public Collection<AccountDto> getAccountsByFilter() {
        return null;
    }
}
