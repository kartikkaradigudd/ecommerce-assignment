package com.ecommerce.services.admin;

import com.ecommerce.model.Order;
import com.ecommerce.responses.admin.SalesStatsResponse;
import com.ecommerce.services.admin.interfaces.AdminService;
import com.ecommerce.storage.CouponCodesStorage;
import com.ecommerce.storage.OrdersStorage;
import com.ecommerce.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    OrdersStorage ordersStorage;

    @Autowired
    CouponCodesStorage couponCodesStorage;


    @Override
    public SalesStatsResponse getSalesStats() {
        SalesStatsResponse response=new SalesStatsResponse();
        try {
            response=prepareStats();
            response.setStatus(Constants.ResponseConstants.OK);
            response.setMessage("Sales stats fetched successfully");

        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(Constants.ResponseConstants.FAILURE);
            response.setMessage("Unable to fetch stats");
        }
        return response;
    }

    private SalesStatsResponse prepareStats(){
        SalesStatsResponse response=new SalesStatsResponse();
        Integer productCount=0;
        Integer totalPurchaseAmount=0;
        List<Order> orders=ordersStorage.getOrders();
        for(Order order:orders){
            productCount+=order.getProductIds().size();
            totalPurchaseAmount+=order.getTotalPaidAmount();
        }
        response.setNoOfItemsPurchased(productCount);
        response.setTotalPurchaseAmount(totalPurchaseAmount);
        response.setCouponCodes(new ArrayList<>(couponCodesStorage.getAllCouponCodes().values()));
        return response;
    }
}
