package com.firrael.test;


import org.springframework.data.repository.CrudRepository;

public interface TestUserRepository extends CrudRepository<TestUser, Long> {
    TestUser findByToken(String token);
}
