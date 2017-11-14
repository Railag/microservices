package com.firrael.token;

import jdk.nashorn.internal.parser.Token;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TokenAppInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String token;
    private String application;

    public TokenAppInfo() {
    }

    public TokenAppInfo(String token, String application) {
        this.token = token;
        this.application = application;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }
}