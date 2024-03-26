package com.exatIt.kata.exaltbank.domain.account;

import com.exatIt.kata.exaltbank.domain.account.entities.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionQueries {

    public List<Transaction> getMonthlyTransactions(UUID accountNumber, LocalDateTime startDate, LocalDateTime endDate);
}
