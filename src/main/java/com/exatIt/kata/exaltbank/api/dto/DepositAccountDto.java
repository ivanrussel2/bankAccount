package com.exatIt.kata.exaltbank.api.dto;

import com.exatIt.kata.exaltbank.utils.CompteEnum;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class DepositAccountDto extends AccountDto {
    private CompteEnum type=CompteEnum.DEPOSIT_ACCOUNT;
    private double overDraftLimit;
    private boolean overdraftAuthorization;

    public DepositAccountDto(UUID accountNumber, double balance, LocalDateTime creationDate, UserDto owner,
                             CompteEnum type, double overDraftLimit, boolean overdraftAuthorization) {
        super(accountNumber, balance, creationDate, owner);
        this.type = type;
        this.overDraftLimit = overDraftLimit;
        this.overdraftAuthorization = overdraftAuthorization;
    }
}
