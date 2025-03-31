package com.ecommerce.model;

import java.util.ArrayList;

public class Order {
    private String username;
    private ArrayList<Integer> productIds;
    private Integer totalPaidAmount;
    private  Integer totalDiscount;

    private String couponCode;

    public Order() {
    }

    public Order(String username, ArrayList<Integer> productIds, Integer totalPaidAmount, Integer totalDiscount, String couponCode) {
        this.username = username;
        this.productIds = productIds;
        this.totalPaidAmount = totalPaidAmount;
        this.totalDiscount = totalDiscount;
        this.couponCode = couponCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(ArrayList<Integer> productIds) {
        this.productIds = productIds;
    }

    public Integer getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(Integer totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    public Integer getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Integer totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}
