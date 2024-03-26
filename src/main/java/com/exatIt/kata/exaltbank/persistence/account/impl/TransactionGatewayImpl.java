package com.exatIt.kata.exaltbank.persistence.account.impl;

import com.exatIt.kata.exaltbank.domain.account.entities.Account;
import com.exatIt.kata.exaltbank.domain.account.entities.Transaction;
import com.exatIt.kata.exaltbank.persistence.account.TransactionGateway;
import com.exatIt.kata.exaltbank.persistence.account.TransactionRepository;
import com.exatIt.kata.exaltbank.persistence.account.entities.TransactionEntity;
import com.exatIt.kata.exaltbank.persistence.mapper.IDaoMapper;
import com.exatIt.kata.exaltbank.utils.AccountTransactionEnum;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TransactionGatewayImpl implements TransactionGateway {

    private final TransactionRepository transactionRepository;
    private final IDaoMapper mapper;
    public TransactionGatewayImpl(TransactionRepository transactionRepository, IDaoMapper mapper) {
        this.transactionRepository = transactionRepository;
        this.mapper = mapper;
    }

    @Override
    public void saveAccountTransactions(Account account, AccountTransactionEnum type, double amount) {
        this.transactionRepository.save(new TransactionEntity(null,account.getAccountNumber(),type,amount,
                LocalDateTime.now()));
    }

    @Override
    public List<Transaction> getMonthlyTransactions(UUID accountNumber, LocalDateTime startDate, LocalDateTime endDate) {
        return transactionRepository.findByAccountNumberAndCreationDateBetweenOrderByCreationDateDesc(accountNumber,
                startDate,endDate).stream().map(transactionEntity -> this.mapper.entityToDomain(transactionEntity))
                .collect(Collectors.toList());
    }

}
