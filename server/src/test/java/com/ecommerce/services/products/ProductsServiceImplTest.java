package com.ecommerce.services.products;

import com.ecommerce.model.Product;
import com.ecommerce.responses.products.ProductsMetaDataResponse;
import com.ecommerce.storage.ProductStorage;
import com.ecommerce.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductsServiceImplTest {
    @Mock
    private ProductStorage productStorage;

    @InjectMocks
    private ProductsServiceImpl productsService;

    private List<Product> mockProducts;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockProducts = Arrays.asList(
                new Product(1, "Laptop", 50000, "laptop.jpg"),
                new Product(2, "Phone", 20000, "phone.jpg")
        );

        when(productStorage.getProducts()).thenReturn(mockProducts);
    }

    @Test
    void testGetAllProducts_Success() {
        ProductsMetaDataResponse response = productsService.getAllProducts();

        assertEquals(Constants.ResponseConstants.OK, response.getStatus());
        assertEquals("Fetched all Products", response.getMessage());
        assertNotNull(response.getProducts());
        assertEquals(2, response.getProducts().size());
    }

    @Test
    void testGetAllProducts_ExceptionHandling() {
        when(productStorage.getProducts()).thenThrow(new RuntimeException("Database error"));

        ProductsMetaDataResponse response = productsService.getAllProducts();

        assertEquals(Constants.ResponseConstants.FAILURE, response.getStatus());
        assertEquals("Failed to fetch products", response.getMessage());
        assertNull(response.getProducts());
    }
}