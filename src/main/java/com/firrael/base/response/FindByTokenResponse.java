package com.firrael.base.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.firrael.base.SimpleResponse;
import com.firrael.base.User;

import java.util.List;

public class FindByTokenResponse extends SimpleResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> messages;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}