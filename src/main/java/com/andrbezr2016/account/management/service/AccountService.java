package com.andrbezr2016.account.management.service;

import com.andrbezr2016.account.management.dto.AccountFilter;
import com.andrbezr2016.account.management.dto.RequestAccountDto;
import com.andrbezr2016.account.management.dto.ResponseAccountDto;

import java.util.Collection;

public interface AccountService {

    ResponseAccountDto createAccount(RequestAccountDto requestAccountDto);

    ResponseAccountDto getAccountById(Long id);

    Collection<ResponseAccountDto> getAccountsByFilter(AccountFilter accountFilter);
}
