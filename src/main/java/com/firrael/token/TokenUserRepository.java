package com.firrael.token;

import org.springframework.data.repository.CrudRepository;

public interface TokenUserRepository extends CrudRepository<TokenUser, Long> {
    TokenUser findByToken(String token);
}
