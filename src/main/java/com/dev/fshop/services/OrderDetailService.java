package com.dev.fshop.services;


import com.dev.fshop.entities.Orders;
import com.dev.fshop.supporters.CartDetail;

import java.util.List;

public interface OrderDetailService {
    public boolean createOrderDetails(List<CartDetail> cartDetailList, Orders orders);
}
