package com.ecommerce.storage;

import com.ecommerce.model.CouponCode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class CouponCodesStorage {
    Map<String, CouponCode> couponCodeMap=new HashMap<>();

    @PostConstruct
    public void init(){
        System.out.println("Initializing In-Memory Coupon Storage Data...");
        couponCodeMap.put("OFF10-SAVE123",new CouponCode("OFF10-SAVE123",new Date(125,4,4),600));
        couponCodeMap.put("SAVE10-PLM45Z",new CouponCode("SAVE10-PLM45Z",new Date(125,5,31),1800));
        couponCodeMap.put("DISCOUNT10-JKH67D",new CouponCode("DISCOUNT10-JKH67D",new Date(125,1,21),6000));
        couponCodeMap.put("TENOFF-YUI89T",new CouponCode("TENOFF-YUI89T",new Date(125,5,25),7500));
        couponCodeMap.put("GET10-MNB23Q",new CouponCode("GET10-MNB23Q",new Date(125,4,6),4200));
    }

    public Map<String, CouponCode> getAllCouponCodes(){
        return couponCodeMap;
    }

}
