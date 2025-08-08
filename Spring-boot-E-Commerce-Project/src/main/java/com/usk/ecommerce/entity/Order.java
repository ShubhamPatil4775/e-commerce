package com.usk.ecommerce.entity;

import com.usk.ecommerce.repository.UserRepository;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDate orderDate;
    private double amount;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private String address;
    private String contactNo;
    private String paymentMode;
    private String transactionId;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItem> orderItems= new ArrayList<>();

    public Order(){
        this.orderDate = LocalDate.now();
        this.orderStatus = OrderStatus.PENDING;
    }

    public Order(User user, double amount, String address, String contactNo, String paymentMode, String transactionId) {
        this();
        this.user = user;
        this.amount = amount;
        this.address = address;
        this.contactNo = contactNo;
        this.paymentMode = paymentMode;
        this.transactionId = transactionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    public void addOrderItem(OrderItem item){
        this.orderItems.add(item);
        item.setOrder(this);
    }
    public void removeOrderItem(OrderItem item){
        this.orderItems.remove(item);
        item.setOrder(null);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", orderDate=" + orderDate +
                ", amount=" + amount +
                ", orderStatus=" + orderStatus +
                ", address='" + address + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", paymentMode='" + paymentMode + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", orderItems=" + orderItems +
                '}';
    }
}
