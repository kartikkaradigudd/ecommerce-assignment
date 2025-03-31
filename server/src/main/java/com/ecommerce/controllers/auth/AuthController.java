package com.ecommerce.controllers.auth;

import com.ecommerce.model.LoginRequest;
import com.ecommerce.responses.common.EcommerceResponse;
import com.ecommerce.responses.common.EcommerceResponseWrapper;
import com.ecommerce.responses.common.StatusResponse;
import com.ecommerce.services.auth.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( value = "/auth" )
public class AuthController {

    EcommerceResponseWrapper responseWrapper=new EcommerceResponseWrapper();

    @Autowired
    AuthService authService;

    @RequestMapping( value = "/login", method = RequestMethod.POST,
            consumes = "application/json" )
    public ResponseEntity<EcommerceResponse> login(@RequestBody LoginRequest loginRequest ) {
        StatusResponse response = authService.login( loginRequest );
        EcommerceResponse ecommerceResponse = responseWrapper.createResponse( response.getStatus(), response );
        return new ResponseEntity<EcommerceResponse>( ecommerceResponse, HttpStatus.OK );
    }

}
