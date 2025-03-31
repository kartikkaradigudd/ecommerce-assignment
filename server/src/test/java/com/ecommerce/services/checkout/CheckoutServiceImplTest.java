package com.ecommerce.services.checkout;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import com.ecommerce.model.CouponCode;
import com.ecommerce.model.User;
import com.ecommerce.responses.checkout.ValidationResponse;
import com.ecommerce.storage.CouponCodesStorage;
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

        // ✅ Mock Coupon Storage
        mockCoupons = new HashMap<>();
        mockCoupons.put("OFF10-SAVE123", new CouponCode("OFF10-SAVE123", new Date(125, 3, 4))); // Valid coupon

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
    void testValidateOrderNoActiveCoupons() {
        when(couponCodesStorage.getAllCouponCodes()).thenReturn(new HashMap<>());

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
}