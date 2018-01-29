package com.firrael.token;

import org.springframework.data.repository.CrudRepository;

public interface TokenAppInfoRepository extends CrudRepository<TokenAppInfo, Long> {
    TokenAppInfo findByToken(String token);
    TokenAppInfo findByUsername(String username);
    boolean existsByUsername(String username);
}
