package com.exatIt.kata.exaltbank.persistence.account;

import com.exatIt.kata.exaltbank.persistence.account.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
}
