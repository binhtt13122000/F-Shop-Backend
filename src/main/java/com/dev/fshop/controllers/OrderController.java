package com.dev.fshop.controllers;

import com.dev.fshop.entities.OrdersEntity;
import com.dev.fshop.services.OrderService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/api")
@Tag(name = "Order")
public class OrderController {
    @Autowired
    private OrderService orderServiceInterface;


    @GetMapping(path = "/users/{userId}/orders")
    public ResponseEntity<List<OrdersEntity>> findListOrderItemByUserId(@PathVariable String userId) {
        return ResponseEntity.ok().body(orderServiceInterface.findListOrderItemByUserId(userId));
    }

    @GetMapping(path = "/orders")
    public ResponseEntity<Float> viewRevenue() {
        return ResponseEntity.ok().body(orderServiceInterface.viewRevenue());
    }

    @PostMapping(path = "/orders")
    public ResponseEntity<OrdersEntity> createNewOrder(@RequestBody OrdersEntity ordersEntity) {
        return ResponseEntity.ok().body(orderServiceInterface.createNewOrder(ordersEntity));
    }


}
