package com.firrael.base.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.firrael.base.SimpleResponse;

public class TokenResponse extends SimpleResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}