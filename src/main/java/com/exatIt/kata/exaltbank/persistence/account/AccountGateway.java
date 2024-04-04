package com.exatIt.kata.exaltbank.persistence.account;

import com.exatIt.kata.exaltbank.domain.account.entities.Account;
import com.exatIt.kata.exaltbank.domain.account.entities.DepositAccount;
import com.exatIt.kata.exaltbank.domain.account.entities.SavingAccount;

import java.util.List;
import java.util.UUID;

public interface AccountGateway {

    public UUID save(Account newAccount);
   // public UUID save(Account newAccount);

    public Account find(UUID accountNumber);


    void updateSaving(SavingAccount account);

    void updateDeposit(DepositAccount account);

    List<Account> list();
}
