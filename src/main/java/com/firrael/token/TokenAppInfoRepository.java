package com.firrael.token;

import org.springframework.data.repository.CrudRepository;

public interface TokenAppInfoRepository extends CrudRepository<TokenAppInfo, Long> {
    TokenAppInfo findByToken(String token);
}
