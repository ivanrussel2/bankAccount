package com.exatIt.kata.exaltbank.persistence.account.entities;

import com.exatIt.kata.exaltbank.utils.AccountTransactionEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor @NoArgsConstructor @Data
public class TransactionEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID accountNumber;
    @Enumerated(EnumType.STRING)
    private AccountTransactionEnum transactionEnum;
    private double amount;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime creationDate =LocalDateTime.now();
}
