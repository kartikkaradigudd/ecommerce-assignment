package com.ecommerce.services.cart;

import com.ecommerce.model.AddToCartRequest;
import com.ecommerce.model.Cart;
import com.ecommerce.model.Product;
import com.ecommerce.model.RemoveProductFromCartRequest;
import com.ecommerce.responses.cart.CartResponse;
import com.ecommerce.responses.common.StatusResponse;
import com.ecommerce.storage.CartStorage;
import com.ecommerce.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class CartServiceImplTest {
    @Mock
    private CartStorage cartStorage;

    @InjectMocks
    private CartServiceImpl cartService;

    private Cart mockCart;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Product product = new Product(1, "Laptop", 50000, "laptop.jpg");
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        mockCart = new Cart();
        mockCart.setProducts(productList);

        Map<String, Cart> cartMap = new HashMap<>();
        cartMap.put("user1", mockCart);

        when(cartStorage.getCarts()).thenReturn(cartMap);
    }

    @Test
    void testGetCartDetailsByUsernameSuccess() {
        CartResponse response = cartService.getCartDetailsByUsername("user1");

        assertEquals(Constants.ResponseConstants.OK, response.getStatus());
        assertEquals("Fetched cart details successfully", response.getMessage());
        assertNotNull(response.getCart());
        assertEquals(1, response.getCart().getProducts().size());
    }

    @Test
    void testGetCartDetailsByUsernameExceptionHandling() {
        when(cartStorage.getCarts()).thenThrow(new RuntimeException("Database error"));

        CartResponse response = cartService.getCartDetailsByUsername("user1");
        assertEquals(Constants.ResponseConstants.FAILURE, response.getStatus());
        assertEquals("Failed to fetch cart information", response.getMessage());
        assertNull(response.getCart());
    }

    @Test
    void testAddToCart_Success() {
        AddToCartRequest request = new AddToCartRequest();
        request.setUsername("user1");
        request.setProduct( new Product(2, "Phone", 20000, "phone.jpg"));

        StatusResponse response = cartService.addToCart(request);

        assertEquals(Constants.ResponseConstants.OK, response.getStatus());
        assertEquals("Successfully added product to the cart", response.getMessage());
    }

    @Test
    void testAddToCartExceptionHandling() {
        AddToCartRequest request = new AddToCartRequest();
        request.setUsername("user1");
        request.setProduct( new Product(2, "Phone", 20000, "phone.jpg"));

        doThrow(new RuntimeException("DB error")).when(cartStorage).addToCart(anyString(), any());

        StatusResponse response = cartService.addToCart(request);

        assertEquals(Constants.ResponseConstants.FAILURE, response.getStatus());
        assertEquals("Failed to add product to Cart", response.getMessage());
    }

    @Test
    void testRemoveProductFromCart_Success() {
        RemoveProductFromCartRequest request = new RemoveProductFromCartRequest();
        request.setUsername("user1");
        request.setProductId(1);
        StatusResponse response = cartService.removeProductFromCart(request);
        assertEquals(Constants.ResponseConstants.OK, response.getStatus());
        assertEquals("Product removed from cart", response.getMessage());
    }

    @Test
    void testRemoveProductFromCart_ExceptionHandling() {
        RemoveProductFromCartRequest request = new RemoveProductFromCartRequest();
        request.setUsername("user1");
        request.setProductId(1);

        doThrow(new RuntimeException("DB error")).when(cartStorage).addToCart(anyString(), any());

        StatusResponse response = cartService.removeProductFromCart(request);

        assertEquals(Constants.ResponseConstants.FAILURE, response.getStatus());
        assertEquals("Failed to remove product", response.getMessage());
    }

}