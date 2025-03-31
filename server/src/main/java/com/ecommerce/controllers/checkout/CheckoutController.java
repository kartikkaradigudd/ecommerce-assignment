package com.ecommerce.controllers.checkout;

import com.ecommerce.responses.checkout.ValidationResponse;
import com.ecommerce.responses.common.EcommerceResponse;
import com.ecommerce.responses.common.EcommerceResponseWrapper;
import com.ecommerce.services.checkout.interfaces.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( value = "/checkout" )
public class CheckoutController {

    EcommerceResponseWrapper responseWrapper=new EcommerceResponseWrapper();

    @Autowired
    CheckoutService checkoutService;

    @RequestMapping( value = "/validateOrder/{username}", method = RequestMethod.GET )
    public ResponseEntity<EcommerceResponse> validateOrder(@PathVariable String username ) {
        ValidationResponse response = checkoutService.validateOrder( username );
        EcommerceResponse ecommerceResponse = responseWrapper.createResponse( response.getStatus(), response );
        return new ResponseEntity<EcommerceResponse>( ecommerceResponse, HttpStatus.OK );
    }

}
