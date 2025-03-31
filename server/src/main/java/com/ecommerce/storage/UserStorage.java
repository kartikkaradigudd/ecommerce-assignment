package com.ecommerce.storage;

import com.ecommerce.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserStorage {
    private final Map<String, User> users = new HashMap<>();
    @PostConstruct
    public void init() {
        System.out.println("Initializing In-Memory User Data...");
        users.put("admin", new User("admin", "admin123", "ADMIN",4));
        users.put("user1", new User("user1", "user123", "USER",9));
        users.put("user2", new User("user2", "user123", "USER",9));
        users.put("user3", new User("user3", "user123", "USER",8));
    }

    public Map<String, User> getUsers(){
        return users;
    }
}
