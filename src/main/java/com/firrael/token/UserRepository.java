package com.firrael.token;

import com.firrael.base.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByToken(String token);
}
