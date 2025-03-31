package com.ecommerce.services.checkout.interfaces;

import com.ecommerce.responses.checkout.ValidationResponse;

public interface CheckoutService {
    ValidationResponse validateOrder(String username);
}
