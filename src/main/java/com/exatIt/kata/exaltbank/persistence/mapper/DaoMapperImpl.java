package com.exatIt.kata.exaltbank.persistence.mapper;

import com.exatIt.kata.exaltbank.domain.account.entities.DepositAccount;
import com.exatIt.kata.exaltbank.domain.account.entities.SavingAccount;
import com.exatIt.kata.exaltbank.domain.account.entities.Transaction;
import com.exatIt.kata.exaltbank.domain.user.User;
import com.exatIt.kata.exaltbank.persistence.account.entities.DepositAccountEntity;
import com.exatIt.kata.exaltbank.persistence.account.entities.SavingAccountEntity;
import com.exatIt.kata.exaltbank.persistence.account.entities.TransactionEntity;
import com.exatIt.kata.exaltbank.persistence.user.UserEntity;
import com.exatIt.kata.exaltbank.utils.CompteEnum;
import org.springframework.stereotype.Component;

@Component
public class DaoMapperImpl implements IDaoMapper{
    @Override
    public SavingAccount entityToDomain(SavingAccountEntity entity) {
        if(entity==null) return null;
        return new SavingAccount(entity.getAccountNumber(), entity.getBalance(), entity.getCreationDate(),
                entityToDomain(entity.getOwner()),CompteEnum.SAVINGS_ACCOUNT,
                entity.getDepositLimit(),entity.isOverdraftAuthorization());
    }
    @Override
    public SavingAccountEntity domainToEntity(SavingAccount account) {
        if(account==null) return null;
        return new SavingAccountEntity(account.getAccountNumber(), account.getBalance(), account.getCreationDate(),
                domainToEntity(account.getOwner()),CompteEnum.SAVINGS_ACCOUNT,
                account.getDepositLimit(),account.isOverdraftAuthorization());
    }

    public DepositAccount entityToDomain(DepositAccountEntity entity) {
        if(entity==null) return null;
        return new DepositAccount(entity.getAccountNumber(), entity.getBalance(), entity.getCreationDate(),
                entityToDomain(entity.getOwner()), CompteEnum.DEPOSIT_ACCOUNT,entity.getOverDraftLimit()
                ,entity.isOverdraftAuthorization());
    }
    @Override
    public DepositAccountEntity domainToEntity(DepositAccount account) {
        if(account==null) return null;
        return new DepositAccountEntity(account.getAccountNumber(), account.getBalance(), account.getCreationDate(),
                domainToEntity(account.getOwner()),CompteEnum.DEPOSIT_ACCOUNT,account.getOverDraftLimit(),
                account.isOverdraftAuthorization());
    }
    @Override
    public User entityToDomain(UserEntity entity) {
        if(entity==null) return null;
        User user = User.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .build();
        return user;
    }
    @Override
    public UserEntity domainToEntity(User user){
        if(user==null) return null;
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        return entity;
    }

    @Override
    public Transaction entityToDomain(TransactionEntity entity) {
        if (entity==null) return null;
        Transaction transaction = new Transaction(entity.getId(),entity.getAccountNumber(),entity.getTransactionEnum(),
                entity.getAmount(),entity.getCreationDate());
        return transaction;
    }

    @Override
    public TransactionEntity domainToEntity(Transaction transaction) {
        if(transaction == null) return null;
        TransactionEntity entity = new TransactionEntity(transaction.getId(),transaction.getAccountNumber(),
                transaction.getTransactionEnum(),transaction.getAmount(),transaction.getCreationDate());
        return entity;
    }
}
