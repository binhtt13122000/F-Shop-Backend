package com.dev.fshop.controllers;

import com.dev.fshop.entities.Orders;
import com.dev.fshop.services.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/api")
@Tag(name = "Order")
public class OrderController {
    @Autowired
    private OrderService orderServiceInterface;


    @GetMapping(path = "/users/{userId}/orders")
    public ResponseEntity<List<Orders>> findListOrderItemByUserId(@PathVariable String userId) {
        return ResponseEntity.ok().body(orderServiceInterface.findListOrderItemByUserId(userId));
    }

    @GetMapping(path = "/orders")
    public ResponseEntity<Float> viewRevenue() {
        return ResponseEntity.ok().body(orderServiceInterface.viewRevenue());
    }

    @PostMapping(path = "/orders")
    public ResponseEntity<Orders> createNewOrder(@RequestBody Orders orders) {
        return ResponseEntity.ok().body(orderServiceInterface.createNewOrder(orders));
    }


}
