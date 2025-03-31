package com.ecommerce.model;

import java.util.Date;

public class CouponCode {
    private String code;
    private Date validTill;

    public CouponCode(String code, Date validTill) {
        this.code = code;
        this.validTill = validTill;
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
}
