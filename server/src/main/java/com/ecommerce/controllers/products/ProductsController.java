package com.ecommerce.controllers.products;


import com.ecommerce.responses.common.EcommerceResponse;
import com.ecommerce.responses.common.EcommerceResponseWrapper;
import com.ecommerce.responses.products.ProductsMetaDataResponse;
import com.ecommerce.services.products.interfaces.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( value = "/products" )
public class ProductsController {

    EcommerceResponseWrapper responseWrapper=new EcommerceResponseWrapper();

    @Autowired
    ProductsService productsService;

    @RequestMapping( value = "/getAllProducts", method = RequestMethod.GET )
    public ResponseEntity<EcommerceResponse> getAllProducts() {
        ProductsMetaDataResponse response = productsService.getAllProducts( );
        EcommerceResponse ecommerceResponse = responseWrapper.createResponse( response.getStatus(), response );
        return new ResponseEntity<EcommerceResponse>( ecommerceResponse, HttpStatus.OK );
    }

}
