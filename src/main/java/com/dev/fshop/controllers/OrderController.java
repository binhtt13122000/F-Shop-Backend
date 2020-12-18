package com.dev.fshop.controller;

import com.dev.fshop.embedded.OrderDetail;
import com.dev.fshop.entities.OrdersEntity;
import com.dev.fshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/api")
public class OrderController {
    @Autowired
    private OrderService orderServiceInterface;

    @GetMapping(path = "/users/{userId}/orders")
    @CrossOrigin
    public ResponseEntity<List<OrdersEntity>> findListOrderItemByUserId(@PathVariable String userId) {
        return ResponseEntity.ok().body(orderServiceInterface.findListOrderItemByUserId(userId));
    }

    @GetMapping(path = "/orders")
    @CrossOrigin
    public ResponseEntity<Float> viewRevenue() {
        return ResponseEntity.ok().body(orderServiceInterface.viewRevenue());
    }

    @PostMapping(path = "/orders")
    @CrossOrigin
    public ResponseEntity<OrdersEntity> createNewOrder(@RequestBody OrdersEntity ordersEntity) {
        return ResponseEntity.ok().body(orderServiceInterface.createNewOrder(ordersEntity));
    }

    @PutMapping(path = "/orders")
    @CrossOrigin
    public ResponseEntity<OrdersEntity> updateOrderExisted(
            @RequestParam Optional<String> orderId,
            @RequestParam Optional<Boolean> status) {
        return null;
    }

}