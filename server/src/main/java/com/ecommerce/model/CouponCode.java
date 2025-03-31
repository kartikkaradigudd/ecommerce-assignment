package com.ecommerce.model;

import java.util.Date;

public class CouponCode {
    private String code;
    private Date validTill;

    private Integer totalDiscountedAmount;

    public CouponCode(String code, Date validTill, Integer totalDiscountedAmount) {
        this.code = code;
        this.validTill = validTill;
        this.totalDiscountedAmount = totalDiscountedAmount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getValidTill() {
        return validTill;
    }

    public void setValidTill(Date validTill) {
        this.validTill = validTill;
    }

    public Integer getTotalDiscountedAmount() {
        return totalDiscountedAmount;
    }

    public void setTotalDiscountedAmount(Integer totalDiscountedAmount) {
        this.totalDiscountedAmount = totalDiscountedAmount;
    }
}
