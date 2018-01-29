package com.firrael.base.response;

import com.firrael.base.SimpleResponse;

public class AddResponse extends SimpleResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
