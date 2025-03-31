package com.ecommerce.services.admin;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ecommerce.model.Order;
import com.ecommerce.responses.admin.SalesStatsResponse;
import com.ecommerce.storage.CouponCodesStorage;
import com.ecommerce.storage.OrdersStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {

    @Mock
    private OrdersStorage ordersStorage;

    @Mock
    private CouponCodesStorage couponCodesStorage;

    @InjectMocks
    private AdminServiceImpl adminService;

    private List<Order> mockOrders;

    @BeforeEach
    void setUp() {
        Order order1 = new Order("user1",new ArrayList<>( Arrays.asList(1, 2)), 100, 10, "SAVE10");
        Order order2 = new Order("user2", new ArrayList<>(Arrays.asList(3, 4, 5)), 200, 20, "OFFER20");

        mockOrders = Arrays.asList(order1, order2);
    }

    @Test
    void getSalesStatsSuccess() {
        when(ordersStorage.getOrders()).thenReturn(mockOrders);
        when(couponCodesStorage.getAllCouponCodes()).thenReturn(Collections.emptyMap());

        SalesStatsResponse response = adminService.getSalesStats();

        assertEquals("OK", response.getStatus());
        assertEquals("Sales stats fetched successfully", response.getMessage());
        assertEquals(5, response.getNoOfItemsPurchased()); // 2 from order1 + 3 from order2
        assertEquals(300, response.getTotalPurchaseAmount()); // 100 + 200
        assertNotNull(response.getCouponCodes());
        assertEquals(0, response.getCouponCodes().size());
    }

    @Test
    void getSalesStatsFailure() {
        when(ordersStorage.getOrders()).thenThrow(new RuntimeException("Storage error"));

        SalesStatsResponse response = adminService.getSalesStats();

        assertEquals("FAILURE", response.getStatus());
        assertEquals("Unable to fetch stats", response.getMessage());
    }
}