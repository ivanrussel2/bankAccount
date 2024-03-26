package com.exatIt.kata.exaltbank.persistence.account.impl;

import com.exatIt.kata.exaltbank.domain.account.entities.Account;
import com.exatIt.kata.exaltbank.domain.account.entities.DepositAccount;
import com.exatIt.kata.exaltbank.domain.account.entities.SavingAccount;
import com.exatIt.kata.exaltbank.persistence.account.entities.DepositAccountEntity;
import com.exatIt.kata.exaltbank.persistence.account.entities.SavingAccountEntity;
import com.exatIt.kata.exaltbank.persistence.mapper.IDaoMapper;
import com.exatIt.kata.exaltbank.persistence.account.AccountGateway;
import com.exatIt.kata.exaltbank.persistence.account.AccountRepository;
import com.exatIt.kata.exaltbank.persistence.account.entities.AccountEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccountGatewayImpl implements AccountGateway {

    private final AccountRepository accountRepository;
    private final IDaoMapper mapper;
    public AccountGatewayImpl(AccountRepository accountRepository, IDaoMapper mapper) {
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    @Override
    public UUID save(Account newAccount) {
        if(newAccount instanceof SavingAccount savingAccount)
        return this.accountRepository.save(this.mapper.domainToEntity(savingAccount)).getAccountNumber();
        else if (newAccount instanceof DepositAccount depositAccount)
        return this.accountRepository.save(this.mapper.domainToEntity(depositAccount)).getAccountNumber();
        return null;
    }

    @Override
    public Account find(UUID accountNumber) {
        AccountEntity accountEntity =accountRepository.findById(accountNumber).orElse(null);
        if(accountEntity instanceof SavingAccountEntity savingAccountEntity){
            return this.mapper.entityToDomain(savingAccountEntity);
        }else if(accountEntity instanceof DepositAccountEntity depositAccountEntity){
            return this.mapper.entityToDomain(depositAccountEntity);
        }
        return null;
    }

    @Override
    public void updateSaving(SavingAccount account) {
        SavingAccountEntity accountEntity = this.mapper.domainToEntity(account);
        this.accountRepository.save(accountEntity);
    }

    @Override
    public void updateDeposit(DepositAccount account) {
        DepositAccountEntity depositAccount =this.mapper.domainToEntity(account);
        this.accountRepository.save(depositAccount);
    }
}
