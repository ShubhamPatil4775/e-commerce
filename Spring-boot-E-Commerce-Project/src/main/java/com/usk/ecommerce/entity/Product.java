package com.usk.ecommerce.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String category;
    private double price;
    private int stockQuantity;
    public Product() {
    }

    public Product(String productName, String category, double price,int stockQuantity) {
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    public void decreaseStock(int quantity){
           if (this.stockQuantity < quantity){
               throw new RuntimeException("Not enough stock");
           }
           this.stockQuantity -= quantity;
    }
    public void increaseStock(int quantity){
        this.stockQuantity += quantity;
    }
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
public static List<Product> storeProducts(){
        return Arrays.asList(
                new Product("Laptop", "Electronics", 50000.0,10),
                new Product("Smartphone", "Electronics", 30000.0,10),
                new Product("Refrigerator", "Appliances", 25000.0,10),
                new Product("Washing Machine", "Appliances", 22000.0,10),
                new Product("Microwave Oven", "Appliances", 8000.0,10),
                new Product("Television", "Electronics", 40000.0,10),
                new Product("Air Conditioner", "Appliances", 35000.0,10),
                new Product("Bluetooth Speaker", "Electronics", 5000.0,20),
                new Product("Smartwatch", "Electronics", 10000.0,5),
                new Product("Tablet", "Electronics", 20000.0,20),
                new Product("Camera", "Electronics", 45000.0,20),
                new Product("Printer", "Electronics", 12000.0,20),
                new Product("Router", "Electronics", 3000.0,20),
                new Product("Gaming Console", "Electronics", 35000.0,20),
                new Product("Hair Dryer", "Personal Care", 1500.0,20),
                new Product("Electric Shaver", "Personal Care", 2500.0,20),
                new Product("Mixer Grinder", "Appliances", 4000.0,20),
                new Product("Induction Cooktop", "Appliances", 3500.0,20),
                new Product("Water Purifier", "Appliances", 12000.0,20),
                new Product("Ceiling Fan", "Appliances", 2500.0,20),
                new Product("Desk Lamp", "Furniture", 1200.0,20),
                new Product("Office Chair", "Furniture", 7000.0,20),
                new Product("Study Table", "Furniture", 9000.0,20),
                new Product("Bookshelf", "Furniture", 6000.0,20),
                new Product("Sofa", "Furniture", 25000.0,20),
                new Product("Bed", "Furniture", 30000.0,20),
                new Product("Wardrobe", "Furniture", 20000.0,20),
                new Product("Dining Table", "Furniture", 18000.0,20),
                new Product("Mattress", "Furniture", 10000.0,20),
                new Product("Curtains", "Home Decor", 2000.0,20),
                new Product("Wall Clock", "Home Decor", 1500.0,20),
                new Product("Painting", "Home Decor", 5000.0,20),
                new Product("Vase", "Home Decor", 800.0,20),
                new Product("Cushion Covers", "Home Decor", 1000.0,20),
                new Product("Floor Lamp", "Home Decor", 3000.0,20),
                new Product("Yoga Mat", "Fitness", 1000.0,20),
                new Product("Treadmill", "Fitness", 40000.0,10),
                new Product("Dumbbells", "Fitness", 2000.0,10),
                new Product("Exercise Bike", "Fitness", 15000.0,10),
                new Product("Resistance Bands", "Fitness", 500.0,10),
                new Product("Cricket Bat", "Sports", 2500.0,10),
                new Product("Football", "Sports", 1000.0,10),
                new Product("Badminton Racket", "Sports", 1500.0,10),
                new Product("Tennis Ball", "Sports", 300.0,10),
                new Product("Skateboard", "Sports", 3500.0,10),
                new Product("Helmet", "Sports", 1200.0,10),
                new Product("Backpack", "Accessories", 2000.0,10),
                new Product("Sunglasses", "Accessories", 2500.0,10),
                new Product("Wristwatch", "Accessories", 5000.0,10),
                new Product("Handbag", "Accessories", 3000.0,10)
                );
}
}
