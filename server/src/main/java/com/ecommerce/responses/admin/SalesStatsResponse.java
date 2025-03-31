package com.ecommerce.responses.admin;

import com.ecommerce.model.CouponCode;
import com.ecommerce.responses.common.StatusResponse;

import java.util.List;

public class SalesStatsResponse  extends StatusResponse {

    private Integer noOfItemsPurchased;
    private Integer totalPurchaseAmount;
    private List<CouponCode> couponCodes;

    public Integer getNoOfItemsPurchased() {
        return noOfItemsPurchased;
    }

    public void setNoOfItemsPurchased(Integer noOfItemsPurchased) {
        this.noOfItemsPurchased = noOfItemsPurchased;
    }

    public Integer getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }

    public void setTotalPurchaseAmount(Integer totalPurchaseAmount) {
        this.totalPurchaseAmount = totalPurchaseAmount;
    }

    public List<CouponCode> getCouponCodes() {
        return couponCodes;
    }

    public void setCouponCodes(List<CouponCode> couponCodes) {
        this.couponCodes = couponCodes;
    }
}
