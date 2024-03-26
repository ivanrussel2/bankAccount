package com.exatIt.kata.exaltbank.persistence.account.entities;

import com.exatIt.kata.exaltbank.persistence.user.UserEntity;
import com.exatIt.kata.exaltbank.utils.CompteEnum;
import jakarta.persistence.*;
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
@Entity
@DiscriminatorValue("DEPOSIT")
public class DepositAccountEntity extends AccountEntity {
    @Transient
    private CompteEnum type=CompteEnum.DEPOSIT_ACCOUNT;
    @Column
    private double overDraftLimit;
    @Column
    private boolean overdraftAuthorization;

    public DepositAccountEntity(UUID accountNumber, double balance, LocalDateTime creationDate, UserEntity owner,
                                CompteEnum type, double overDraftLimit, boolean overdraftAuthorization) {
        super(accountNumber, balance, creationDate, owner);
        this.type = type;
        this.overDraftLimit = overDraftLimit;
        this.overdraftAuthorization = overdraftAuthorization;
    }
}
