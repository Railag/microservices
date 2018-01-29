package com.firrael.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class SimpleResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;

    public boolean hasErrors() {
        return error != null && error.length() > 0;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}