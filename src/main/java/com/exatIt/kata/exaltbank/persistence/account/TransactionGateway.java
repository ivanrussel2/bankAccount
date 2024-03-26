package com.exatIt.kata.exaltbank.persistence.account;

import com.exatIt.kata.exaltbank.domain.account.entities.Account;
import com.exatIt.kata.exaltbank.domain.account.entities.Transaction;
import com.exatIt.kata.exaltbank.utils.AccountTransactionEnum;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionGateway {
    public void saveAccountTransactions(Account account, AccountTransactionEnum type, double amount);

    public List<Transaction> getMonthlyTransactions(UUID accountNumber, LocalDateTime startDate, LocalDateTime endDate);
}
