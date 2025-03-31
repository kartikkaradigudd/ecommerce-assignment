package com.ecommerce.services.checkout;

import com.ecommerce.model.CouponCode;
import com.ecommerce.model.User;
import com.ecommerce.responses.checkout.ValidationResponse;
import com.ecommerce.services.checkout.interfaces.CheckoutService;
import com.ecommerce.storage.CouponCodesStorage;
import com.ecommerce.storage.UserStorage;
import com.ecommerce.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    UserStorage userStorage;

    @Autowired
    CouponCodesStorage couponCodesStorage;

    @Value("${nthOrder}")
    private Integer nthOrder;


    @Override
    public ValidationResponse validateOrder(String username) {
        ValidationResponse response=new ValidationResponse();
        try {
            User user = userStorage.getUsers().get(username);
            if(user!=null){
                if((user.getOrderCount()+1)%nthOrder==0){
                    String code=getActiveAndRandomCouponCode();
                    response.setValidForCoupon(code != null);
                    response.setCouponCode(getActiveAndRandomCouponCode());

                }else{
                    response.setValidForCoupon(false);
                }
                response.setStatus(Constants.ResponseConstants.OK);
                response.setMessage("Order Validated Successfully");
            }
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(Constants.ResponseConstants.FAILURE);
            response.setMessage("Failed to Validate order");
        }
        return response;
    }

    private String getActiveAndRandomCouponCode(){
        Map<String, CouponCode> couponCodeMap=couponCodesStorage.getAllCouponCodes();

        Date currentDate = new Date();

        // Filtering active coupons
        List<CouponCode> activeCoupons = new ArrayList<>();
        for (CouponCode coupon : couponCodeMap.values()) {
            if (coupon.getValidTill().after(currentDate)) {
                activeCoupons.add(coupon);
            }
        }

        // If no active coupons found
        if (activeCoupons.isEmpty()) {
            return null;
        }

        // Taking a random active coupon
        Random random = new Random();
        return activeCoupons.get(random.nextInt(activeCoupons.size())).getCode();
    }
}
