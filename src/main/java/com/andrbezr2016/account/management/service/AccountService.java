package com.andrbezr2016.account.management.service;

import com.andrbezr2016.account.management.dto.AccountFilter;
import com.andrbezr2016.account.management.dto.RequestAccountDto;
import com.andrbezr2016.account.management.dto.ResponseAccountDto;
import com.andrbezr2016.account.management.entity.Account;
import com.andrbezr2016.account.management.mapper.AccountMapper;
import com.andrbezr2016.account.management.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public ResponseAccountDto createAccount(RequestAccountDto requestAccountDto) {
        Account account = accountRepository.save(accountMapper.toEntity(requestAccountDto));
        return accountMapper.toDto(account);
    }

    public ResponseAccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        return accountMapper.toDto(account);
    }

    public Collection<ResponseAccountDto> getAccountsByFilter(AccountFilter accountFilter) {
        List<Account> accountList = accountRepository.findAll(filter(accountFilter));
        return accountMapper.toDtoList(accountList);
    }

    private static Specification<Account> filter(AccountFilter accountFilter) {
        List<Specification<Account>> specificationList = new ArrayList<>();
        if (accountFilter.getLastName() != null) {
            specificationList.add((accountRoot, query, builder) -> builder.equal(accountRoot.get("lastName"), accountFilter.getLastName()));
        }
        if (accountFilter.getFirstName() != null) {
            specificationList.add((accountRoot, query, builder) -> builder.equal(accountRoot.get("firstName"), accountFilter.getFirstName()));
        }
        if (accountFilter.getMiddleName() != null) {
            specificationList.add((accountRoot, query, builder) -> builder.equal(accountRoot.get("middleName"), accountFilter.getMiddleName()));
        }
        if (accountFilter.getPhoneNumber() != null) {
            specificationList.add((accountRoot, query, builder) -> builder.equal(accountRoot.get("phoneNumber"), accountFilter.getPhoneNumber()));
        }
        if (accountFilter.getEmail() != null) {
            specificationList.add((accountRoot, query, builder) -> builder.equal(accountRoot.get("email"), accountFilter.getEmail()));
        }
        return specificationList.isEmpty() ? Specification.where((accountRoot, query, builder) -> builder.or()) : Specification.allOf(specificationList);
    }
}
