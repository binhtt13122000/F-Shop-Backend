package com.dev.fshop.services;


import com.dev.fshop.entities.Orders;

import java.util.List;

public interface OrderService {
    public Orders findOrderByOrderId(String orderId);

    public List<Orders> getOrdersByProductIdAndUserId(String productId, String userId);
}
