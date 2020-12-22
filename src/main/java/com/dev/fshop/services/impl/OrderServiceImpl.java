package com.dev.fshop.services.impl;

import com.dev.fshop.entities.Orders;
import com.dev.fshop.repositories.OrdersReposity;
import com.dev.fshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersReposity ordersReposity;


    @Override
    public List<Orders> findListOrderItemByUserId(String userId) {
//        return ordersReposity.findOrdersEntityByCustomerEntity(userId);
        return null;

    }

    @Override
    public float viewRevenue() {
        return 0;
//        return ordersReposity.viewRevenue();
    }

    @Override
    public Orders createNewOrder(Orders orders) {
//        OrderDetailEntity checkExisted = orderDetailReposity.findById(orderDetailEntity.getOrderId()).orElse(null);
//        if(checkExisted == null) {
//
//        }
        return ordersReposity.save(orders);
    }

    @Override
    public Orders updateOrderExisted(String orderId, Boolean status) {
        Orders checkExisted = ordersReposity.findById(orderId).orElse(null);
        if(checkExisted == null) {

        }
//        return ordersReposity.updateStatusOrder(orderId,status);
        return null;

    }


}
