package com.dev.fshop.services;


import com.dev.fshop.entities.*;
import com.dev.fshop.supporters.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface OrderService {
    public Orders findOrderByOrderIdWithAdminAndSeller(String orderId);

    public Orders findOrderByOrderIdWithUserId(String orderId, String userId);

    public Page<Orders> getOrdersWithParameters(String userId, Date dateFrom, Date dateTo, float priceFrom, float priceTo, boolean isAdmin, Pageable pageable);

    public Page<Orders> getOrdersWithUserId(String userId, boolean isAdmin, Pageable pageable);

    public List<Orders> getOrdersByProductIdAndUserId(String productId, String userId, boolean isAdmin);

    public Orders createNewOrders(Account customInfo, Account accountBuyer, Promotion promotion, Cart cart);

    public Orders createNewOrderByProduct(Account customInfo, Account accountBuyer, Promotion promotion, ProductDetail productDetail, Product product, int quantity);

    public boolean changeStatusOrders(Orders currentOrders, int status);

}
