package com.ecommerce.services.products;

import com.ecommerce.responses.products.ProductsMetaDataResponse;
import com.ecommerce.services.products.interfaces.ProductsService;
import com.ecommerce.storage.InMemoryProductStorage;
import com.ecommerce.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    InMemoryProductStorage productStorage;

    @Override
    public ProductsMetaDataResponse getAllProducts() {
        ProductsMetaDataResponse response=new ProductsMetaDataResponse();
        try {
            response.setProducts(productStorage.getProducts());
            response.setStatus(Constants.ResponseConstants.OK);
            response.setMessage("Fetched all Products");
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(Constants.ResponseConstants.FAILURE);
            response.setMessage("Failed to fetch products");
        }
        return response;
    }
}
