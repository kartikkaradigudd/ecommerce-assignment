package com.ecommerce.services.cart.intefaces;

import com.ecommerce.model.AddToCartRequest;
import com.ecommerce.model.RemoveProductFromCartRequest;
import com.ecommerce.responses.cart.CartResponse;
import com.ecommerce.responses.common.StatusResponse;

public interface CartService {

    CartResponse getCartDetailsByUsername(String username);
    StatusResponse removeProductFromCart(RemoveProductFromCartRequest request);
    StatusResponse addToCart(AddToCartRequest request);
}
