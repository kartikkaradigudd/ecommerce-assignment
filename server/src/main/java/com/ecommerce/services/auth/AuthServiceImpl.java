package com.ecommerce.services.auth;

import com.ecommerce.model.LoginRequest;
import com.ecommerce.model.User;
import com.ecommerce.responses.auth.UserAuthResponse;
import com.ecommerce.responses.common.StatusResponse;
import com.ecommerce.services.auth.interfaces.AuthService;
import com.ecommerce.storage.UserStorage;
import com.ecommerce.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserStorage userStorage;

    @Override
    public StatusResponse login(LoginRequest loginRequest) {
        UserAuthResponse response=new UserAuthResponse();
        try {
            User user=authenticate(loginRequest);
            if(user!=null){
                response.setUser(user);
                response.setStatus(Constants.ResponseConstants.OK);
                response.setMessage("Login Successful!!");
            }else{
                response.setStatus(Constants.ResponseConstants.FAILURE);
                response.setMessage("Check your credentials and try again.");
            }
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(Constants.ResponseConstants.FAILURE);
            response.setMessage("Some error occurred while login");
        }
        return response;
    }

    public User authenticate(LoginRequest loginRequest){
        Map<String, User> users = userStorage.getUsers();
        User user=users.get(loginRequest.getUserName());

        if(user!=null && user.getPassword().equals(loginRequest.getPassword())){
            return user;
        }
        return null;
    }
}
