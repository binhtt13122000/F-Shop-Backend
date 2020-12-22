package com.dev.fshop.services;


import com.dev.fshop.entities.Orders;

import java.util.List;

public interface OrderService {
    //Get Methods
    public List<Orders> findListOrderItemByUserId(String userId);
    public float viewRevenue();

    //Post Methods
    public Orders createNewOrder(Orders orderDetailEntity);

    //Put Methods
    public Orders updateOrderExisted(String orderId, Boolean status);

    //Delete Methods

}
