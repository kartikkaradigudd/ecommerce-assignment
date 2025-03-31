package com.ecommerce.services.checkout;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import com.ecommerce.model.Cart;
import com.ecommerce.model.CouponCode;
import com.ecommerce.model.Order;
import com.ecommerce.model.User;
import com.ecommerce.responses.checkout.ValidationResponse;
import com.ecommerce.responses.common.StatusResponse;
import com.ecommerce.storage.CartStorage;
import com.ecommerce.storage.CouponCodesStorage;
import com.ecommerce.storage.OrdersStorage;
import com.ecommerce.storage.UserStorage;
import com.ecommerce.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

class CheckoutServiceImplTest {

    @Mock
    private UserStorage userStorage;

    @Mock
    private OrdersStorage ordersStorage;

    @Mock
    private CartStorage cartStorage;

    @Mock
    private CouponCodesStorage couponCodesStorage;

    @InjectMocks
    private CheckoutServiceImpl checkoutService;

    private User mockUser;
    private Map<String, CouponCode> mockCoupons;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // nthOrder set to 3 (for testing)
        ReflectionTestUtils.setField(checkoutService, "nthOrder", 3);

        mockUser = new User("user1", "user123","USER",2); // 2 orders, next is 3rd (valid for coupon)


        mockCoupons = new HashMap<>();
        mockCoupons.put("OFF10-SAVE123", new CouponCode("OFF10-SAVE123", new Date(125, 3, 4),500)); // Valid coupon

        Map<String, User> userMap = new HashMap<>();
        userMap.put("user1", mockUser);
        when(userStorage.getUsers()).thenReturn(userMap);
        when(couponCodesStorage.getAllCouponCodes()).thenReturn(mockCoupons);
    }

    @Test
    void testValidateOrderEligibleForCoupon() {

        ValidationResponse response = checkoutService.validateOrder("user1");

        assertEquals(Constants.ResponseConstants.OK, response.getStatus());
        assertEquals("Order Validated Successfully", response.getMessage());
        assertTrue(response.getValidForCoupon());
        assertNotNull(response.getCouponCode());
    }

    @Test
    void testValidateOrderNotEligibleForCoupon() {
        mockUser.setOrderCount(1); // 2nd order (not 3rd)

        ValidationResponse response = checkoutService.validateOrder("user1");

        assertEquals(Constants.ResponseConstants.OK, response.getStatus());
        assertEquals("Order Validated Successfully", response.getMessage());
        assertFalse(response.getValidForCoupon());
        assertNull(response.getCouponCode());
    }


    @Test
    void testValidateOrderExceptionHandling() {
        when(userStorage.getUsers()).thenThrow(new RuntimeException("Database error"));

        ValidationResponse response = checkoutService.validateOrder("user1");

        assertEquals(Constants.ResponseConstants.FAILURE, response.getStatus());
        assertEquals("Failed to Validate order", response.getMessage());
    }

    @Test
    void testPlaceOrderSuccessWithCoupon() {

        Order order = new Order("user1", new ArrayList<>(), 500, 50, "SAVE10");

        HashMap<String, Cart> cartMap = new HashMap<>();
        cartMap.put("user1", new Cart());

        HashMap<String, User> userMap = new HashMap<>();
        User user = new User();
        user.setOrderCount(2);
        userMap.put("user1", user);

        HashMap<String, CouponCode> couponMap = new HashMap<>();
        CouponCode coupon = new CouponCode();
        coupon.setTotalDiscountedAmount(100);
        couponMap.put("SAVE10", coupon);

        when(cartStorage.getCarts()).thenReturn(cartMap);
        when(userStorage.getUsers()).thenReturn(userMap);
        when(ordersStorage.getOrders()).thenReturn(new ArrayList<>());
        when(couponCodesStorage.getAllCouponCodes()).thenReturn(couponMap);

        StatusResponse response = checkoutService.placeOrder(order);

        assertEquals(Constants.ResponseConstants.OK, response.getStatus());
        assertEquals("Order Placed Successfully", response.getMessage());
        assertEquals(3, userStorage.getUsers().get("user1").getOrderCount()); // Order count should increase
        assertTrue(cartStorage.getCarts().get("user1").getProducts().isEmpty()); // Cart should be emptied
        assertEquals(150, couponCodesStorage.getAllCouponCodes().get("SAVE10").getTotalDiscountedAmount()); // Discount should be updated

        verify(ordersStorage, times(1)).getOrders();
        verify(cartStorage, times(2)).getCarts();
        verify(userStorage, times(3)).getUsers();
        verify(couponCodesStorage, times(3)).getAllCouponCodes();
    }

    @Test
    void testPlaceOrderSuccessWithoutCoupon() {

        Order order = new Order("user2", new ArrayList<>(), 800, 100, null); // No coupon code

        HashMap<String, Cart> cartMap = new HashMap<>();
        cartMap.put("user2", new Cart());

        HashMap<String, User> userMap = new HashMap<>();
        User user = new User();
        user.setOrderCount(1);
        userMap.put("user2", user);

        when(cartStorage.getCarts()).thenReturn(cartMap);
        when(userStorage.getUsers()).thenReturn(userMap);
        when(ordersStorage.getOrders()).thenReturn(new ArrayList<>());

        StatusResponse response = checkoutService.placeOrder(order);

        assertEquals(Constants.ResponseConstants.OK, response.getStatus());
        assertEquals("Order Placed Successfully", response.getMessage());
        assertEquals(2, userStorage.getUsers().get("user2").getOrderCount()); // Order count should increase
        assertTrue(cartStorage.getCarts().get("user2").getProducts().isEmpty()); // Cart should be emptied

        verify(ordersStorage, times(1)).getOrders();
        verify(cartStorage, times(2)).getCarts();
        verify(userStorage, times(3)).getUsers();
        verify(couponCodesStorage, never()).getAllCouponCodes(); // Coupon storage should not be accessed
    }

    @Test
    void testPlaceOrderFailure() {
        Order order = new Order("user1", new ArrayList<>(), 500, 50, "SAVE10");

        when(cartStorage.getCarts()).thenThrow(new RuntimeException("Database error"));

        StatusResponse response = checkoutService.placeOrder(order);

        assertEquals(Constants.ResponseConstants.FAILURE, response.getStatus());
        assertEquals("Unable to Place Order", response.getMessage());

        verify(cartStorage, times(1)).getCarts();
    }
}