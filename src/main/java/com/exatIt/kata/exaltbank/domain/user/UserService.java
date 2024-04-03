package com.exatIt.kata.exaltbank.domain.user;

import com.exatIt.kata.exaltbank.persistence.user.UserGateway;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserQueries{

    private final UserGateway userGateway;

    public UserService(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public Long create(User newUser) {
        return userGateway.save(newUser);
    }

    @Override
    public User read(Long id) {
        return userGateway.findById(id);
    }
}
