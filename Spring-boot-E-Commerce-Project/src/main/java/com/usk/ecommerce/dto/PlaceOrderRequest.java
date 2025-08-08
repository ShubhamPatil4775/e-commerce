package com.usk.ecommerce.dto;

public class PlaceOrderRequest {
    private String deliveryAddress;
    private String deliveryContactNo;
    private String paymentMethod;
    private String accountNo;
    public PlaceOrderRequest() {
    }

    public PlaceOrderRequest(String deliveryAddress, String deliveryContactNo, String paymentMethod,String accountNo) {
        this.deliveryAddress = deliveryAddress;
        this.deliveryContactNo = deliveryContactNo;
        this.paymentMethod = paymentMethod;
        this.accountNo = accountNo;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryContactNo() {
        return deliveryContactNo;
    }

    public void setDeliveryContactNo(String deliveryContactNo) {
        this.deliveryContactNo = deliveryContactNo;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
}
