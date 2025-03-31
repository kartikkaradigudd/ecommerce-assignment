package com.ecommerce.controllers.cart;

import com.ecommerce.model.AddToCartRequest;
import com.ecommerce.model.LoginRequest;
import com.ecommerce.model.RemoveProductFromCartRequest;
import com.ecommerce.responses.cart.CartResponse;
import com.ecommerce.responses.common.EcommerceResponse;
import com.ecommerce.responses.common.EcommerceResponseWrapper;
import com.ecommerce.responses.common.StatusResponse;
import com.ecommerce.services.cart.intefaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( value = "/cart" )
public class CartController {

    EcommerceResponseWrapper responseWrapper=new EcommerceResponseWrapper();

    @Autowired
    CartService cartService;

    @RequestMapping( value = "/getCartDetailsByUsername/{username}", method = RequestMethod.GET )
    public ResponseEntity<EcommerceResponse> getCartDetailsByUsername(@PathVariable String username ) {
        CartResponse response = cartService.getCartDetailsByUsername( username );
        EcommerceResponse ecommerceResponse = responseWrapper.createResponse( response.getStatus(), response );
        return new ResponseEntity<EcommerceResponse>( ecommerceResponse, HttpStatus.OK );
    }

    @RequestMapping( value = "/removeProductFromCart", method = RequestMethod.POST,
            consumes = "application/json" )
    public ResponseEntity<EcommerceResponse> removeProductFromCart(@RequestBody RemoveProductFromCartRequest request ) {
        StatusResponse response = cartService.removeProductFromCart( request );
        EcommerceResponse ecommerceResponse = responseWrapper.createResponse( response.getStatus(), response );
        return new ResponseEntity<EcommerceResponse>( ecommerceResponse, HttpStatus.OK );
    }

    @RequestMapping( value = "/addProductToCart", method = RequestMethod.POST,
            consumes = "application/json" )
    public ResponseEntity<EcommerceResponse> addProductToCart(@RequestBody AddToCartRequest request ) {
        StatusResponse response = cartService.addToCart( request );
        EcommerceResponse ecommerceResponse = responseWrapper.createResponse( response.getStatus(), response );
        return new ResponseEntity<EcommerceResponse>( ecommerceResponse, HttpStatus.OK );
    }



}
