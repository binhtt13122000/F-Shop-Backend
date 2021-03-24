package com.dev.fshop.services;


import com.dev.fshop.entities.Orders;
import com.dev.fshop.entities.Product;
import com.dev.fshop.supporters.CartDetail;
import com.dev.fshop.supporters.OrderDetail;
import com.dev.fshop.supporters.ProductDetail;

import java.util.List;

public interface OrderDetailService {
    public boolean createOrderDetails(List<CartDetail> cartDetailList, Orders orders);

    public boolean createOrderDetailsByAProduct(int quantity, Product product, ProductDetail productDetail, Orders orders);

    public List<OrderDetail> getListOrderDetailsByOrder(Orders orders);
}
