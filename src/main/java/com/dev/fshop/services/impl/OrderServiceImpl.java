package com.dev.fshop.services.impl;

import com.dev.fshop.entity.OrdersEntity;
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
    public List<OrdersEntity> findListOrderItemByUserId(String userId) {
        return ordersReposity.findListOrderItemByUserId(userId);
    }

    @Override
    public float viewRevenue() {
        return ordersReposity.viewRevenue();
    }

    @Override
    public OrdersEntity createNewOrder(OrdersEntity ordersEntity) {
//        OrderDetailEntity checkExisted = orderDetailReposity.findById(orderDetailEntity.getOrderId()).orElse(null);
//        if(checkExisted == null) {
//
//        }
        return ordersReposity.insertOrderWithEntityManager(ordersEntity);
    }

    @Override
    public OrdersEntity updateOrderExisted(String orderId, Boolean status) {
        OrdersEntity checkExisted = ordersReposity.findById(orderId).orElse(null);
        if(checkExisted == null) {

        }
        return ordersReposity.updateStatusOrder(orderId,status);
    }


}
