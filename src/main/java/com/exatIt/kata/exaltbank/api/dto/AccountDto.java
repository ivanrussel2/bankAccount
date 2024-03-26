package com.exatIt.kata.exaltbank.api.dto;

import com.exatIt.kata.exaltbank.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor @Data @ToString
public class AccountDto {
    private UUID accountNumber;
    private double balance;
    private LocalDateTime creationDate = LocalDateTime.now();
    private UserDto owner;
}
