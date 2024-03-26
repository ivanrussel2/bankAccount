package com.exatIt.kata.exaltbank.domain.user;

import com.exatIt.kata.exaltbank.domain.account.entities.Account;
import lombok.*;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Data @ToString @Builder
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private List<Account> accounts;
}
