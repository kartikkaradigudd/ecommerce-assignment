package com.ecommerce.storage;

import com.ecommerce.model.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class OrdersStorage {

    List<Order> orders=new ArrayList<>();

    @PostConstruct
    public void init(){
        System.out.println("Initializing In-Memory Order Data...");
        orders.add(new Order("user1", new ArrayList<>(Arrays.asList(1, 2)), 9000, 100, "OFF10-SAVE123"));
        orders.add(new Order("user2", new ArrayList<>(Arrays.asList(4, 3)), 16000,1600 , "TENOFF-YUI89T"));
    }

    public List<Order> getOrders() {
        return orders;
    }
}
