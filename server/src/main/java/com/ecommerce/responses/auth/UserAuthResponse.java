package com.ecommerce.responses.auth;

import com.ecommerce.model.User;
import com.ecommerce.responses.common.StatusResponse;

public class UserAuthResponse extends StatusResponse {
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
