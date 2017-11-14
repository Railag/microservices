package com.firrael.gateway;

import com.firrael.base.User;

import javax.persistence.Entity;

@Entity(name = "gatewayUser")
public class GatewayUser extends User {

    public GatewayUser() {
    }

    public GatewayUser(String name, String token, String application) {
        super(name, token, application);
    }
}