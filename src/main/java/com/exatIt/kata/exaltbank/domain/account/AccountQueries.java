package com.exatIt.kata.exaltbank.domain.account;

import com.exatIt.kata.exaltbank.domain.account.entities.Account;
import com.exatIt.kata.exaltbank.domain.account.entities.DepositAccount;
import com.exatIt.kata.exaltbank.domain.account.entities.SavingAccount;
import com.exatIt.kata.exaltbank.domain.user.UserNotFoundException;

import java.util.List;
import java.util.UUID;

public interface AccountQueries {

    public Account find (UUID accountNumber);
   // public Account create(Account newAccount);
    public UUID create(DepositAccount newAccount) throws UserNotFoundException;
    public UUID create(SavingAccount newAccount) throws UserNotFoundException;

    public boolean withdraw(UUID accountNumber, double amount) throws AccountException, AccountNotFoundException;

    public boolean deposit(UUID accountNumber, double amount) throws AccountException;

    public List<Account> list();

}
