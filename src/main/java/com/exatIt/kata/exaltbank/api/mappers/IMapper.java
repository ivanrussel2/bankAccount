package com.exatIt.kata.exaltbank.api.mappers;

import com.exatIt.kata.exaltbank.api.dto.DepositAccountDto;
import com.exatIt.kata.exaltbank.api.dto.SavingAccountDto;
import com.exatIt.kata.exaltbank.api.dto.TransactionDto;
import com.exatIt.kata.exaltbank.api.dto.UserDto;
import com.exatIt.kata.exaltbank.domain.account.entities.DepositAccount;
import com.exatIt.kata.exaltbank.domain.account.entities.SavingAccount;
import com.exatIt.kata.exaltbank.domain.account.entities.Transaction;
import com.exatIt.kata.exaltbank.domain.user.User;

public interface IMapper {

    DepositAccount dtoToDomain(DepositAccountDto dto);

    DepositAccountDto domainToDto(DepositAccount account);

    SavingAccount dtoToDomain(SavingAccountDto dto);

    SavingAccountDto domainToDto(SavingAccount account);

    User dtoToDomain(UserDto dto);

    UserDto domainToDto(User user);
    Transaction dtoToDomain(TransactionDto dto);

    TransactionDto domainToDto(Transaction transaction);



}
