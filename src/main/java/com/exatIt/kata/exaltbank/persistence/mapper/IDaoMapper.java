package com.exatIt.kata.exaltbank.persistence.mapper;

import com.exatIt.kata.exaltbank.domain.account.entities.DepositAccount;
import com.exatIt.kata.exaltbank.domain.account.entities.SavingAccount;
import com.exatIt.kata.exaltbank.domain.account.entities.Transaction;
import com.exatIt.kata.exaltbank.domain.user.User;
import com.exatIt.kata.exaltbank.persistence.account.entities.DepositAccountEntity;
import com.exatIt.kata.exaltbank.persistence.account.entities.SavingAccountEntity;
import com.exatIt.kata.exaltbank.persistence.account.entities.TransactionEntity;
import com.exatIt.kata.exaltbank.persistence.user.UserEntity;

public interface IDaoMapper {
  //  Account entityToDomain(AccountEntity entity);

   // AccountEntity domainToEntity(Account account);

    SavingAccount entityToDomain(SavingAccountEntity entity);

    DepositAccount entityToDomain(DepositAccountEntity entity);

    SavingAccountEntity domainToEntity(SavingAccount account);

    DepositAccountEntity domainToEntity(DepositAccount account);

    User entityToDomain(UserEntity entity);

    UserEntity domainToEntity(User user);

    Transaction entityToDomain(TransactionEntity entity);

    TransactionEntity domainToEntity(Transaction user);


}
