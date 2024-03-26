package com.exatIt.kata.exaltbank.api.mappers;

import com.exatIt.kata.exaltbank.api.dto.DepositAccountDto;
import com.exatIt.kata.exaltbank.api.dto.SavingAccountDto;
import com.exatIt.kata.exaltbank.api.dto.TransactionDto;
import com.exatIt.kata.exaltbank.api.dto.UserDto;
import com.exatIt.kata.exaltbank.domain.account.entities.DepositAccount;
import com.exatIt.kata.exaltbank.domain.account.entities.SavingAccount;
import com.exatIt.kata.exaltbank.domain.account.entities.Transaction;
import com.exatIt.kata.exaltbank.domain.user.User;
import com.exatIt.kata.exaltbank.utils.CompteEnum;
import org.springframework.stereotype.Component;

@Component
public class MapperImpl implements IMapper{
    @Override
    public DepositAccount dtoToDomain(DepositAccountDto dto) {
        if(dto==null) return null;
        DepositAccount account = new DepositAccount(dto.getAccountNumber(), dto.getBalance(), dto.getCreationDate(),
                dtoToDomain(dto.getOwner()),dto.getType(),dto.getOverDraftLimit(),dto.isOverdraftAuthorization());
           account.setType(CompteEnum.DEPOSIT_ACCOUNT);
           account.setOverDraftLimit(dto.getOverDraftLimit());
           account.setOverdraftAuthorization(dto.isOverdraftAuthorization());
        return account;
    }

    @Override
    public SavingAccount dtoToDomain(SavingAccountDto dto) {
        if(dto==null) return null;
        SavingAccount account = new SavingAccount(dto.getAccountNumber(), dto.getBalance(), dto.getCreationDate(),
                dtoToDomain(dto.getOwner()),dto.getType(),dto.getDepositLimit(),dto.isOverdraftAuthorization());
            account.setDepositLimit(dto.getDepositLimit());
            account.setType(dto.getType());
            account.setOverdraftAuthorization(dto.isOverdraftAuthorization());
        return account;
    }



    @Override
    public SavingAccountDto domainToDto(SavingAccount account) {
        if(account==null) return null;
        return new SavingAccountDto(account.getAccountNumber(), account.getBalance(), account.getCreationDate(),
                domainToDto(account.getOwner()),CompteEnum.SAVINGS_ACCOUNT,account.getDepositLimit(),account.isOverdraftAuthorization());

    }

    @Override
    public DepositAccountDto domainToDto(DepositAccount account) {
        if(account==null) return null;
        return new DepositAccountDto(account.getAccountNumber(), account.getBalance(), account.getCreationDate(),
                domainToDto(account.getOwner()),account.getType(),account.getOverDraftLimit(),account.isOverdraftAuthorization());
    }
    @Override
    public User dtoToDomain(UserDto dto) {
        if (dto==null) return null;
        User user = User.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();
        return user;
    }
    @Override
    public UserDto domainToDto(User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }

    @Override
    public Transaction dtoToDomain(TransactionDto dto) {
        if(dto==null) return null;
        Transaction transaction = new Transaction(dto.getId(),dto.getAccountNumber(),dto.getTransactionEnum(),
                dto.getAmount(),dto.getCreationDate());
        return transaction;
    }

    @Override
    public TransactionDto domainToDto(Transaction transaction) {
        if(transaction==null) return null;
        TransactionDto dto = new TransactionDto(transaction.getId(),transaction.getAccountNumber(),transaction.getTransactionEnum(),
                transaction.getAmount(),transaction.getCreationDate());
        return dto;
    }
}
