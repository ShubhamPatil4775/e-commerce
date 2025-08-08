package com.usk.ecommerce.dto;

import com.usk.ecommerce.entity.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public class OrderResponse {
    private Long orderId;
    private Long userId;
    private LocalDate orderDate;
    private double totalAmount;
    private OrderStatus orderStatus;
    private String address;
    private String contactNo;
    private String paymentMode;
    private String transactionId;
    private List<OrderItemResponse> itemResponses;

    public OrderResponse() {
    }

    public OrderResponse(Long orderId, Long userId, LocalDate orderDate, double totalAmount, OrderStatus orderStatus, String address, String contactNo, String paymentMode, String transactionId, List<OrderItemResponse> itemResponses) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.address = address;
        this.contactNo = contactNo;
        this.paymentMode = paymentMode;
        this.transactionId = transactionId;
        this.itemResponses = itemResponses;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public List<OrderItemResponse> getItemResponses() {
        return itemResponses;
    }

    public void setItemResponses(List<OrderItemResponse> itemResponses) {
        this.itemResponses = itemResponses;
    }
}
