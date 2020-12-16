package com.dev.fshop.controller;

import com.dev.fshop.entity.OrderDetailEntity;
import com.dev.fshop.entity.OrderItemEntity;
import com.dev.fshop.services.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/f-shop/v1/api")
public class OrderController {
    @Autowired
    private OrderServiceInterface orderServiceInterface;

    @GetMapping(path = "/orders/{orderId}")
    public List<OrderItemEntity> findListOrderItemByOrderId(@PathVariable String orderId) {
        return orderServiceInterface.findListOrderItemByOrderId(orderId);
    }

    @PostMapping(path = "/orders")
    public OrderDetailEntity createNewOrder(@RequestBody OrderDetailEntity orderDetailEntity) {
        return orderServiceInterface.createNewOrder(orderDetailEntity);
    }

    @PatchMapping(path = "/orders/orderitems/{orderItemId}/products/{quantity}")
    public OrderDetailEntity updateQuantityProduct(@PathVariable Integer quantity, @PathVariable Integer orderItemId) {
        return orderServiceInterface.updateQuantityProduct(quantity, orderItemId);
    }

    @DeleteMapping(path = "/orders")
    public boolean deleteOrderByCustomer(@RequestParam(name = "userId")String userId, @RequestBody List<OrderDetailEntity> orderDetailEntities) {
        return orderServiceInterface.deleteOrderByCustomer(userId, orderDetailEntities);
    }

    @DeleteMapping(path = "/orders/orderItems/{orderItemId}")
    public boolean deleteItemInOrder(@PathVariable Integer orderItemId) {
        return orderServiceInterface.deleteItemInOrder(orderItemId);
    }
}
