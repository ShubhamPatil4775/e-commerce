package com.usk.ecommerce.service;

import com.usk.ecommerce.dto.OrderResponse;
import com.usk.ecommerce.dto.PlaceOrderRequest;

import java.util.List;

public interface OrderService {
    OrderResponse placeOrder(Long userId, PlaceOrderRequest request);
    List<OrderResponse> getOrderByUserId(Long userId);
    OrderResponse getOrderById(Long userId, Long orderId);
}
