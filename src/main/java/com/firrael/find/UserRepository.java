package com.firrael.find;

import com.firrael.base.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByToken(String token);

    default User findUserByToken(String token) {
        User user = findByToken(token);
        return user;
    };
}