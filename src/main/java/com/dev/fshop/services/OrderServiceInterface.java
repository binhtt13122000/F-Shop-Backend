package com.dev.fshop.services;


import com.dev.fshop.entity.OrderDetailEntity;
import com.dev.fshop.entity.OrderItemEntity;

import java.util.List;

public interface OrderServiceInterface {
    //Get Methods
    public List<OrderItemEntity> findListOrderItemByOrderId(Integer orderId);

    //Post Methods
    public OrderDetailEntity createNewOrder(OrderDetailEntity orderDetailEntity);

    //Put Methods
    public OrderDetailEntity updateQuantityProduct(Integer quantity, Integer orderItemId);

    //Delete Methods
    public boolean deleteOrderByCustomer(String userId, List<OrderDetailEntity> orderDetail);
    public boolean deleteItemInOrder(int orderItemId);
}
