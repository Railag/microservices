package com.firrael.gateway;

import org.springframework.data.repository.CrudRepository;

public interface GatewayUserRepository extends CrudRepository<GatewayUser, Long> {
    GatewayUser findByToken(String token);
}
