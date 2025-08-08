package com.usk.ecommerce.dto;

public class OrderItemResponse {
    private Long orderItemId;
    private Long productId;
    private String productName;
    private double purchasePrice;
    private int quantity;
    private double itemSubtotal;

    public OrderItemResponse() {
    }

    public OrderItemResponse(Long orderItemId, Long productId, String productName, double purchasePrice, int quantity, double itemSubtotal) {
        this.orderItemId = orderItemId;
        this.productId = productId;
        this.productName = productName;
        this.purchasePrice = purchasePrice;
        this.quantity = quantity;
        this.itemSubtotal = itemSubtotal;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getItemSubtotal() {
        return itemSubtotal;
    }

    public void setItemSubtotal(double itemSubtotal) {
        this.itemSubtotal = itemSubtotal;
    }
}
