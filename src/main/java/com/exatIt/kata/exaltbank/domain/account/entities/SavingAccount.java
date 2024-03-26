package com.exatIt.kata.exaltbank.domain.account.entities;

import com.exatIt.kata.exaltbank.domain.user.User;
import com.exatIt.kata.exaltbank.utils.CompteEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor @Data @ToString
public class SavingAccount extends Account {
    private CompteEnum type=CompteEnum.SAVINGS_ACCOUNT;
    private double depositLimit;
    private boolean overdraftAuthorization=false;

    public SavingAccount(UUID accountNumber, double balance, LocalDateTime creationDate, User owner, CompteEnum type,
                         double depositLimit, boolean overdraftAuthorization) {
        super(accountNumber, balance, creationDate, owner);
        this.type = type;
        this.depositLimit = depositLimit;
        this.overdraftAuthorization = overdraftAuthorization;
    }
}
