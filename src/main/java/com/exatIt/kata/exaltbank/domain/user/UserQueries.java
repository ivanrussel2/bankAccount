package com.exatIt.kata.exaltbank.domain.user;

public interface UserQueries {

    public Long create(User newUser);

    public User read(Long id);

}
