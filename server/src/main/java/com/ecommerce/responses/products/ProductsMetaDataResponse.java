package com.ecommerce.responses.products;

import com.ecommerce.model.Product;
import com.ecommerce.responses.common.StatusResponse;

import java.util.List;

public class ProductsMetaDataResponse extends StatusResponse {
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
