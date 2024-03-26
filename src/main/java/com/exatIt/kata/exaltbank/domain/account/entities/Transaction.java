package com.exatIt.kata.exaltbank.domain.account.entities;

import com.exatIt.kata.exaltbank.utils.AccountTransactionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor @Data
public class Transaction {
    private Long id;
    private UUID accountNumber;
    private AccountTransactionEnum transactionEnum;
    private double amount;
    private LocalDateTime creationDate ;


}
