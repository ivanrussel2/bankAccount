package com.exatIt.kata.exaltbank.api.dto;

import com.exatIt.kata.exaltbank.utils.AccountTransactionEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor @Data
public class TransactionDto {
    private Long id;
    private UUID accountNumber;
    private AccountTransactionEnum transactionEnum;
    private double amount;
    private LocalDateTime creationDate ;
}
