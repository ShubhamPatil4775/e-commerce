package com.usk.ecommerce.controller;

import com.usk.ecommerce.dto.PlaceOrderRequest;
import com.usk.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v4")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder/{userId}")
    public ResponseEntity<?> placeOrder(@PathVariable Long userId, @RequestBody PlaceOrderRequest request){
        return new ResponseEntity<>(orderService.placeOrder(userId,request), HttpStatus.CREATED);
    }

    @GetMapping("/getOrder/{userId}")
    public ResponseEntity<?> getOrder(@PathVariable Long userId){
        return new ResponseEntity<>(orderService.getOrderByUserId(userId),HttpStatus.OK);
    }
    @GetMapping("/getOrders")
    public ResponseEntity<?> getOrderByIds(@RequestParam Long userId,@RequestParam Long orderId){
        return new ResponseEntity<>(orderService.getOrderById(userId,orderId),HttpStatus.OK);
    }

}
