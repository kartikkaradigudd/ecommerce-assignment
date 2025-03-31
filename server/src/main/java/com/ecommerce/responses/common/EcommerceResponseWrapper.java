package com.ecommerce.responses.common;

public class EcommerceResponseWrapper {

    public EcommerceResponse createResponse( String status, Object content ) {
        EcommerceResponse ecommerceResponse = new EcommerceResponse();
        ecommerceResponse.setStatus( status );
        ecommerceResponse.setContent( content );
        return ecommerceResponse;
    }
}
