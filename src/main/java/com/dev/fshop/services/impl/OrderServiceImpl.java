package com.dev.fshop.services.impl;

import com.dev.fshop.embedded.OrderDetail;
import com.dev.fshop.entity.OrdersEntity;
import com.dev.fshop.repositories.OrderDetailReposity;
import com.dev.fshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailReposity orderDetailReposity;


    @Override
    public List<OrdersEntity> findListOrderItemByUserId(String userId) {
        return orderDetailReposity.findListOrderItemByUserId(userId);
    }

    @Override
    public float viewRevenue() {
        return orderDetailReposity.viewRevenue();
    }

    @Override
    public OrdersEntity createNewOrder(OrdersEntity ordersEntity) {
//        OrderDetailEntity checkExisted = orderDetailReposity.findById(orderDetailEntity.getOrderId()).orElse(null);
//        if(checkExisted == null) {
//
//        }
        return orderDetailReposity.insertOrderWithEntityManager(ordersEntity);
    }

    @Override
    public OrdersEntity updateOrderExisted(String orderId, Boolean status) {
        OrdersEntity checkExisted = orderDetailReposity.findById(orderId).orElse(null);
        if(checkExisted == null) {

        }
        return orderDetailReposity.updateStatusOrder(orderId,status);
    }


}
