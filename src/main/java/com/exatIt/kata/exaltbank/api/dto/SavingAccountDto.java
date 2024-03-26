package com.exatIt.kata.exaltbank.api.dto;

import com.exatIt.kata.exaltbank.utils.CompteEnum;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor @Data @ToString @Builder
public class SavingAccountDto extends AccountDto {
    private CompteEnum type=CompteEnum.SAVINGS_ACCOUNT;
    private double depositLimit;
    private boolean overdraftAuthorization=false;

    public SavingAccountDto(UUID accountNumber, double balance, LocalDateTime creationDate,
                            UserDto owner, CompteEnum type, double depositLimit, boolean overdraftAuthorization) {
        super(accountNumber, balance, creationDate, owner);
        this.type = type;
        this.depositLimit = depositLimit;
        this.overdraftAuthorization = overdraftAuthorization;
    }
}
