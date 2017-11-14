package com.firrael.test;

import com.firrael.base.User;

import javax.persistence.Entity;

@Entity(name = "testUser")
public class TestUser extends User {

    public TestUser() {
    }

    public TestUser(String name, String token, String application) {
        super(name, token, application);
    }
}
