package com.firrael.base.response;

import com.firrael.base.SimpleResponse;
import com.firrael.base.User;

public class UserResponse extends SimpleResponse {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
