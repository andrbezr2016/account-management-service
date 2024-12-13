package com.andrbezr2016.account.management.mapper;

import com.andrbezr2016.account.management.dto.ResponseAccountDto;
import com.andrbezr2016.account.management.dto.RequestAccountDto;
import com.andrbezr2016.account.management.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    ResponseAccountDto toDto(Account account);

    List<ResponseAccountDto> toDtoList(List<Account> accountList);

    Account toEntity(RequestAccountDto requestAccountDto);
}
