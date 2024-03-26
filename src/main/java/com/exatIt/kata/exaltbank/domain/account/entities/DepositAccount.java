package com.exatIt.kata.exaltbank.domain.account.entities;

import com.exatIt.kata.exaltbank.domain.user.User;
import com.exatIt.kata.exaltbank.utils.CompteEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DepositAccount extends Account{
    private CompteEnum type=CompteEnum.DEPOSIT_ACCOUNT;
    private double overDraftLimit;
    private boolean overdraftAuthorization;

    public DepositAccount(UUID accountNumber, double balance, LocalDateTime creationDate, User owner, CompteEnum type,
                          double overDraftLimit, boolean overdraftAuthorization) {
        super(accountNumber, balance, creationDate, owner);
        this.type = type;
        this.overDraftLimit = overDraftLimit;
        this.overdraftAuthorization = overdraftAuthorization;
    }
}
