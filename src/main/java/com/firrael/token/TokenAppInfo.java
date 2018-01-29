package com.firrael.token;

import jdk.nashorn.internal.parser.Token;

import javax.persistence.*;

@Entity
public class TokenAppInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String token;

    private String application;

    public TokenAppInfo() {
    }

    public TokenAppInfo(String username, String token, String application) {
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}