package com.exatIt.kata.exaltbank.persistence.account;

import com.exatIt.kata.exaltbank.persistence.account.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {
    List<TransactionEntity> findByAccountNumberAndCreationDateBetweenOrderByCreationDateDesc(UUID accountNumber,
                                                                                             LocalDateTime startDate,
                                                                                             LocalDateTime endDate);

}
