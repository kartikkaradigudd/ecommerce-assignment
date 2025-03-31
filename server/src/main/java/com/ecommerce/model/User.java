package com.ecommerce.model;

public class User {
    private String username;
    private String password;
    private String role;
    private Integer orderCount;

    public User(String username, String password, String role, Integer orderCount) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.orderCount = orderCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }
}
