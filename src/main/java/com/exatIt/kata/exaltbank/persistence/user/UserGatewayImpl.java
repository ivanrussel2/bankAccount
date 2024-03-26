package com.exatIt.kata.exaltbank.persistence.user;

import com.exatIt.kata.exaltbank.domain.user.User;
import com.exatIt.kata.exaltbank.persistence.mapper.IDaoMapper;
import org.springframework.stereotype.Component;

@Component
public class UserGatewayImpl implements UserGateway {
    private final UserRepository userRepository;
    private final IDaoMapper mapper;

    public UserGatewayImpl(UserRepository userRepository, IDaoMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public User findById(Long id) {
        return mapper.entityToDomain(userRepository.findById(id).orElse(null));
    }
}
