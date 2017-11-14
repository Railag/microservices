package com.firrael.token;

import com.firrael.base.User;

import javax.persistence.Entity;

@Entity(name = "tokenUser")
public class TokenUser extends User {

    public TokenUser() {
    }

    public TokenUser(String name, String token, String application) {
        super(name, token, application);
    }
}