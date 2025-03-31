package com.ecommerce.services.auth.interfaces;

import com.ecommerce.model.LoginRequest;
import com.ecommerce.responses.common.StatusResponse;

public interface AuthService {
    StatusResponse login(LoginRequest loginRequest);
}
