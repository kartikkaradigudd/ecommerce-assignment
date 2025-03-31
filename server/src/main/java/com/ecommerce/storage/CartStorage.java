package com.ecommerce.storage;

import com.ecommerce.model.Cart;
import com.ecommerce.model.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class CartStorage {

    //map<username,Cart>
    Map<String, Cart> cartMap=new HashMap<>();

    @PostConstruct
    public void init(){
        System.out.println("Initializing In-Memory Cart Data...");
        cartMap.put("admin", new Cart("admin",new ArrayList<>()));
        cartMap.put("user1", new Cart("user1",new ArrayList<>(Arrays.asList(new Product(1,"Laptop",50000,"assets/images/laptop.jpeg"),new Product(2,"Phone",20000,"assets/images/phone.jpeg")))));
        cartMap.put("user2", new Cart("user2",new ArrayList<>()));
        cartMap.put("user3", new Cart("user3",new ArrayList<>()));
    }


    public Map<String, Cart> getCarts(){
        return cartMap;
    }

    public void addToCart(String username, Cart cart){
        cartMap.put(username,cart);
    }


}
