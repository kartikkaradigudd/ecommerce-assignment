package com.ecommerce.storage;

import com.ecommerce.model.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductStorage {
    private final List<Product> products=new ArrayList<>();
    @PostConstruct
    void init(){
        System.out.println("Initializing In-Memory Product Data...");
        products.add(new Product(1,"Laptop",50000,"assets/images/laptop.jpeg"));
        products.add(new Product(2,"Phone",20000,"assets/images/phone.jpeg"));
        products.add(new Product(3,"T-Shirt",600,"assets/images/t-shirt.jpeg"));
        products.add(new Product(4,"Jeans",1200,"assets/images/jeans.jpeg"));
        products.add(new Product(5,"Microwave",13500,"assets/images/microwave.jpeg"));
        products.add(new Product(6,"Vacuum Cleaner",4500,"assets/images/vacuum-cleaner.jpeg"));
    }

    public List<Product> getProducts(){
        return products;
    }
}
