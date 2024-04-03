package com.exatIt.kata.exaltbank.persistence.user;

import com.exatIt.kata.exaltbank.domain.user.User;

public interface UserGateway {

    User findById(Long id);

    Long save(User newUser);
}
