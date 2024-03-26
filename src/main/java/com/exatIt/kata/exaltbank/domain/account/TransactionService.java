package com.exatIt.kata.exaltbank.domain.account;

import com.exatIt.kata.exaltbank.domain.account.entities.Transaction;
import com.exatIt.kata.exaltbank.persistence.account.TransactionGateway;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Service
public class TransactionService implements TransactionQueries {

    private final TransactionGateway transactionGateway;

    public TransactionService(TransactionGateway transactionGateway) {
        this.transactionGateway = transactionGateway;
    }

    @Override
    public List<Transaction> getMonthlyTransactions(UUID accountNumber, LocalDateTime startDate, LocalDateTime endDate) {
        return transactionGateway.getMonthlyTransactions(accountNumber, startDate, endDate);
    }
}
