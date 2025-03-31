package com.ecommerce.services.checkout.interfaces;

import com.ecommerce.model.Order;
import com.ecommerce.responses.checkout.ValidationResponse;
import com.ecommerce.responses.common.StatusResponse;

public interface CheckoutService {
    ValidationResponse validateOrder(String username);

    StatusResponse placeOrder(Order request);

}
