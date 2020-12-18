package com.dev.fshop.services.impl;

import com.dev.fshop.entity.OrdersEntity;
import com.dev.fshop.repositories.OrdersRepository;
import com.dev.fshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepository ordersRepository;


    @Override
    public List<OrdersEntity> findListOrderItemByUserId(String userId) {
//        return ordersRepository.findListOrderItemByUserId(userId);
        return null;
    }

    @Override
    public float viewRevenue() {
//        return ordersRepository.viewRevenue();
        return 1;
    }

    @Override
    public OrdersEntity createNewOrder(OrdersEntity ordersEntity) {
//        OrderDetailEntity checkExisted = orderDetailReposity.findById(orderDetailEntity.getOrderId()).orElse(null);
//        if(checkExisted == null) {
//
//        }
//        return ordersRepository.insertOrderWithEntityManager(ordersEntity);
        return null;
    }

    @Override
    public OrdersEntity updateOrderExisted(String orderId, Boolean status) {
        OrdersEntity checkExisted = ordersRepository.findById(orderId).orElse(null);
        if(checkExisted == null) {

        }
//        return ordersRepository.updateStatusOrder(orderId,status);
        return null;
    }


}
