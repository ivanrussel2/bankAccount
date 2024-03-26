package com.exatIt.kata.exaltbank.domain.account.entities;

import com.exatIt.kata.exaltbank.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor @Data @ToString
public class Account {
    private UUID accountNumber;
    private double balance;
    private LocalDateTime creationDate = LocalDateTime.now();
    private User owner;
}
