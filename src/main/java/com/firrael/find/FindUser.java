package com.firrael.find;

import com.firrael.base.User;

import javax.persistence.Entity;

@Entity(name = "findUser")
public class FindUser extends User {

    public FindUser() {
    }

    public FindUser(String name, String token, String application) {
        super(name, token, application);
    }
}