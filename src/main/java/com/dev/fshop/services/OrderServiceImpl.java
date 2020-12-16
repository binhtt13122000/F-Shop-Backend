package com.dev.fshop.services;

import com.dev.fshop.entity.OrderDetailEntity;
import com.dev.fshop.entity.OrderItemEntity;
import com.dev.fshop.repositories.OrderDetailReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderServiceInterface{

    @Autowired
    private OrderDetailReposity orderDetailReposity;
    @Override
    public List<OrderItemEntity> findListOrderItemByOrderId(Integer orderId) {
        return orderDetailReposity.findOrder(orderId);
    }

    @Override
    public OrderDetailEntity createNewOrder(OrderDetailEntity orderDetailEntity) {
//        OrderDetailEntity checkExisted = orderDetailReposity.findById(orderDetailEntity.getOrderId()).orElse(null);
//        if(checkExisted == null) {
//
//        }
        return orderDetailReposity.insertOrderWithEntityManager(orderDetailEntity);
    }

    @Override
    public OrderDetailEntity updateQuantityProduct(Integer quantity, Integer orderItemId) {
//        OrderDetailEntity checkExisted = orderDetailReposity.findById(orderItemId).orElse(null);
//        if(checkExisted == null) {
//
//        }
        return orderDetailReposity.updateQuanProduct(quantity, orderItemId);
    }

    @Override
    public boolean deleteOrderByCustomer(String userId, List<OrderDetailEntity> orderDetail) {
        //        OrderDetailEntity checkExisted = orderDetailReposity.findById(orderDetailEntity.getOrderId()).orElse(null);
//        if(checkExisted == null) {
//
//        }
        orderDetailReposity.deleteOrderByCustomer(userId, orderDetail);
        return true;
    }

    @Override
    public boolean deleteItemInOrder(int orderItemId) {
        orderDetailReposity.deleteItemInOrder(orderItemId);
        return true;
    }
}
