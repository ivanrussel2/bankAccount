package com.exatIt.kata.exaltbank.persistence.account.entities;

import com.exatIt.kata.exaltbank.persistence.user.UserEntity;
import com.exatIt.kata.exaltbank.utils.CompteEnum;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor @Data @ToString

@DiscriminatorValue("SAVING")
@Entity
public class SavingAccountEntity extends AccountEntity {
    @Transient
    private CompteEnum type=CompteEnum.SAVINGS_ACCOUNT;
    @Column
    private double depositLimit;
    @Column
    private boolean overdraftAuthorization=false;

    public SavingAccountEntity(UUID accountNumber, double balance, LocalDateTime creationDate, UserEntity owner,
                               CompteEnum type, double depositLimit, boolean overdraftAuthorization) {
        super(accountNumber, balance, creationDate, owner);
        this.type = type;
        this.depositLimit = depositLimit;
        this.overdraftAuthorization = overdraftAuthorization;
    }
}
