package com.ecommerce.services.auth;

import com.ecommerce.model.LoginRequest;
import com.ecommerce.model.User;
import com.ecommerce.responses.auth.UserAuthResponse;
import com.ecommerce.responses.common.StatusResponse;
import com.ecommerce.storage.InMemoryUserStorage;
import com.ecommerce.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AuthServiceImplTest {
    @Mock
    private InMemoryUserStorage userStorage;

    @InjectMocks
    private AuthServiceImpl authService;

    private Map<String, User> mockUsers;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);

        mockUsers = new HashMap<>();
        mockUsers.put("admin", new User("admin", "admin123", "ADMIN"));
        mockUsers.put("user1", new User("user1", "user123", "USER"));

        when(userStorage.getUsers()).thenReturn(mockUsers);
    }

    @Test
    void testSuccessfulLogin() {
        LoginRequest request = new LoginRequest("admin", "admin123");

        UserAuthResponse response = (UserAuthResponse) authService.login(request);

        assertEquals(Constants.ResponseConstants.OK, response.getStatus());
        assertEquals("Login Successful!!", response.getMessage());
        assertNotNull(response.getUser());
        assertEquals("admin", response.getUser().getUsername());
    }

    @Test
    void testFailedLogin_InvalidCredentials() {
        LoginRequest request = new LoginRequest("admin", "wrongpassword");

        UserAuthResponse response = (UserAuthResponse) authService.login(request);

        assertEquals(Constants.ResponseConstants.FAILURE, response.getStatus());
        assertEquals("Check your credentials and try again.", response.getMessage());
        assertNull(response.getUser());
    }

    @Test
    void testLogin_ExceptionHandling() {
        when(userStorage.getUsers()).thenThrow(new RuntimeException("Database error"));

        LoginRequest request = new LoginRequest("admin", "admin123");

        UserAuthResponse response = (UserAuthResponse) authService.login(request);

        assertEquals(Constants.ResponseConstants.FAILURE, response.getStatus());
        assertEquals("Some error occurred while login", response.getMessage());
        assertNull(response.getUser());
    }

}