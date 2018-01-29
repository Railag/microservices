package com.firrael.base.response;

import com.firrael.base.SimpleResponse;

public class ApplicationResponse extends SimpleResponse {
    private String application;

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }
}