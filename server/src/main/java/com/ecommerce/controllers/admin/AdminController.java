package com.ecommerce.controllers.admin;

import com.ecommerce.responses.admin.SalesStatsResponse;
import com.ecommerce.responses.common.EcommerceResponse;
import com.ecommerce.responses.common.EcommerceResponseWrapper;
import com.ecommerce.responses.products.ProductsMetaDataResponse;
import com.ecommerce.services.admin.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( value = "/admin" )
public class AdminController {

    EcommerceResponseWrapper responseWrapper=new EcommerceResponseWrapper();

    @Autowired
    AdminService adminService;

    @RequestMapping( value = "/getSalesStats", method = RequestMethod.GET )
    public ResponseEntity<EcommerceResponse> getSalesStats() {
        SalesStatsResponse response = adminService.getSalesStats( );
        EcommerceResponse ecommerceResponse = responseWrapper.createResponse( response.getStatus(), response );
        return new ResponseEntity<EcommerceResponse>( ecommerceResponse, HttpStatus.OK );
    }
}
