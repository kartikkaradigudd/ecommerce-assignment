package com.ecommerce.responses.checkout;

import com.ecommerce.responses.common.StatusResponse;

public class ValidationResponse extends StatusResponse {
    private Boolean isValidForCoupon;
    private String couponCode;

    public Boolean getValidForCoupon() {
        return isValidForCoupon;
    }

    public void setValidForCoupon(Boolean validForCoupon) {
        isValidForCoupon = validForCoupon;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}
