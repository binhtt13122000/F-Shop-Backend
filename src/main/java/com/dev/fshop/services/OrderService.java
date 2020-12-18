package com.dev.fshop.services;


import com.dev.fshop.embedded.OrderDetail;
import com.dev.fshop.entities.OrdersEntity;

import java.util.List;

public interface OrderService {
    //Get Methods
    public List<OrdersEntity> findListOrderItemByUserId(String userId);
    public float viewRevenue();

    //Post Methods
    public OrdersEntity createNewOrder(OrdersEntity orderDetailEntity);

    //Put Methods
    public OrdersEntity updateOrderExisted(String orderId, Boolean status);

    //Delete Methods

}
