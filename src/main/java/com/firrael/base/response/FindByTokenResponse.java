package com.firrael.base.response;

import com.firrael.base.SimpleResponse;
import com.firrael.base.User;

import java.util.List;

public class FindByTokenResponse extends SimpleResponse {

    private User user;
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