package com.usk.ecommerce.controller;

import com.usk.ecommerce.dto.OrderItemResponse;
import com.usk.ecommerce.dto.OrderResponse;
import com.usk.ecommerce.dto.PlaceOrderRequest;
import com.usk.ecommerce.entity.OrderStatus;
import com.usk.ecommerce.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.when;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private PlaceOrderRequest request;
    private OrderResponse response;

    @BeforeEach
    void setUp() {
        request = new PlaceOrderRequest();
        request.setAccountNo("1234567890");
        request.setDeliveryAddress("Test Address");
        request.setDeliveryContactNo("9999999999");
        request.setPaymentMethod("BANK");

        OrderItemResponse item = new OrderItemResponse();
        item.setOrderItemId(1L);
        item.setProductId(100L);
        item.setProductName("Test Product");
        item.setPurchasePrice(250.0);
        item.setQuantity(2);
        item.setItemSubtotal(500.0);

        response = new OrderResponse();
        response.setOrderId(10L);
        response.setUserId(1L);
        response.setOrderDate(LocalDate.now());
        response.setTotalAmount(500.0);
        response.setOrderStatus(OrderStatus.DONE);
        response.setAddress("Test Address");
        response.setContactNo("9999999999");
        response.setPaymentMode("BANK");
        response.setTransactionId("10101010");
        response.setItemResponses(List.of(item));
    }

    @Test
    void testPlaceOrder_Success() {
        when(orderService.placeOrder(1L, request)).thenReturn(response);

        ResponseEntity<OrderResponse> result = orderController.placeOrder(1L, request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(orderService).placeOrder(1L, request);
    }
    @Test
    void testGetOrder_Success() {
        List<OrderResponse> mockOrders = List.of(response);
        when(orderService.getOrderByUserId(1L)).thenReturn(mockOrders);

        ResponseEntity<?> result = orderController.getOrder(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockOrders, result.getBody());
        verify(orderService).getOrderByUserId(1L);
    }
    @Test
    void testGetOrderByIds_Success() {
        OrderResponse mockOrder = response;

        when(orderService.getOrderById(1L, 10L)).thenReturn(mockOrder);

        ResponseEntity<?> result = orderController.getOrderByIds(1L, 10L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockOrder, result.getBody());
        verify(orderService).getOrderById(1L, 10L);
    }


}
