package com.ecommerce.responses.cart;

import com.ecommerce.model.Cart;
import com.ecommerce.responses.common.StatusResponse;

public class CartResponse extends StatusResponse {
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
