package com.firrael.find;


import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<FindUser, Long> {
    FindUser findByToken(String token);
}
