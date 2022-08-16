package com.dev.fshop.services.impl;

import com.dev.fshop.entities.*;
import com.dev.fshop.repositories.OrdersRepository;
import com.dev.fshop.services.OrderService;
import com.dev.fshop.supporters.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public Orders findOrderByOrderIdWithAdminAndSeller(String orderId) {
        return ordersRepository.findById(orderId).orElse(null);
    }

    @Override
    public Orders findOrderByOrderIdWithUserId(String orderId, String userId) {
        return ordersRepository.getOrdersByOrderIdAndAccount_UserIdAndStatusGreaterThanEqual(orderId, userId, 0);
    }

    @Override
    public Page<Orders> getOrdersWithParameters(String userId, Date dateFrom, Date dateTo, int status, boolean isAdmin, Pageable pageable) {
        if(status == 2) {
            if (isAdmin) {
                return ordersRepository.getAllListOrdersByParameters(userId, dateFrom, dateTo, -1, pageable);
            } else {
                return ordersRepository.getAllListOrdersByParameters(userId, dateFrom, dateTo, -1, pageable);
            }
        }else {
            if (isAdmin) {
                return ordersRepository.getListOrdersByParametersAndStatus(userId, dateFrom, dateTo, status, pageable);
            } else {
                return ordersRepository.getListOrdersByParametersAndStatus(userId, dateFrom, dateTo, status, pageable);
            }
        }
    }

    @Override
    public Page<Orders> getOrdersWithUserId(String userId, boolean isAdmin, int status, Pageable pageable) {
        if(status == 2) {
            if (isAdmin) {
                return ordersRepository.getAllOrderByUserIdWithAdmin(pageable);
            } else {
                return ordersRepository.getAllOrderByUserIdWithUser(userId, -1, pageable);
            }
        }else {
            if (isAdmin) {
                return ordersRepository.getAllOrderByUserIdWithAdmin(pageable);
            } else {
                return ordersRepository.getAllOrderByUserIdAndStatusWithUser(userId, status, pageable);
            }
        }
    }

    @Override
    public List<Orders> getOrdersByProductIdAndUserId(String productId, String userId, boolean isAdmin) {
        if (isAdmin) {
            return ordersRepository.getOrdersByProductIdAndUserIdAndStatus(productId, userId, -1);
        } else {
            return ordersRepository.getOrdersByProductIdAndUserIdAndStatus(productId, userId, 0);
        }
    }

    @Override
    @Transactional
    public Orders createNewOrders(Account customInfo, Account accountBuyer, Promotion promotion, Cart cart) {
        Orders orders  = new Orders();
        orders.setAccount(accountBuyer);
        orders.setSeller(null);
        orders.setFullname(customInfo.getName());
        orders.setPhoneNumber(customInfo.getPhoneNumber());
        orders.setAddress(customInfo.getAddress());
        orders.setPromotion(promotion);
        if(promotion == null) {
            orders.setOrderTotal(cart.getCartTotal());
        }else {
            orders.setOrderTotal(cart.getCartTotal() - (cart.getCartTotal() * promotion.getPromo() / 100));
        }
        orders.setCreateAt(new Date());
        orders.setOnline(true);
        orders.setStatus(0);
        return ordersRepository.save(orders);
    }

    @Override
    public Orders createNewOrderByProduct(Account customInfo, Account accountBuyer, Promotion promotion, ProductDetail productDetail, Product product, int quantity) {
        Orders orders = new Orders();
        orders.setAccount(accountBuyer);
        orders.setSeller(null);
        orders.setFullname(customInfo.getName());
        orders.setPhoneNumber(customInfo.getPhoneNumber());
        orders.setAddress(customInfo.getAddress());
        orders.setPromotion(promotion);
        if(promotion == null) {
            orders.setOrderTotal(quantity * product.getProductPrice());
        }else {
            orders.setOrderTotal(quantity * product.getProductPrice() - (quantity * product.getProductPrice() * promotion.getPromo() / 100));
        }
        orders.setCreateAt(new Date());
        orders.setOnline(true);
        orders.setStatus(0);
        return ordersRepository.save(orders);
    }

    @Override
    public boolean changeStatusOrders(Orders currentOrders, int status) {
        currentOrders.setStatus(status);
        ordersRepository.save(currentOrders);
        return true;
    }
}
