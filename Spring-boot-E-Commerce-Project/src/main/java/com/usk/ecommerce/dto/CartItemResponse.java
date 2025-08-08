package com.usk.ecommerce.dto;

public class CartItemResponse {
    private Long cartItemId;
    private Long productId;
    private String productName;
    private Double price;
    private Double priceAtAddTime;
    private int quantity;
    private Double subTotal;

    public CartItemResponse() {
    }

    public CartItemResponse(Long cartItemId, Long productId, String productName, Double price, Double priceAtAddTime, int quantity, Double subTotal) {
        this.cartItemId = cartItemId;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.priceAtAddTime = priceAtAddTime;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    public CartItemResponse(Long id, Long id1, String productName, double purchasePrice, int quantity, double v) {
    }


    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPriceAtAddTime() {
        return priceAtAddTime;
    }

    public void setPriceAtAddTime(Double priceAtAddTime) {
        this.priceAtAddTime = priceAtAddTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
}
