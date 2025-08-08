package com.usk.ecommerce.repository;

import com.usk.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductName(String productName);
    List<Product> findByCategoryIgnoreCase(String category);
    List<Product> findByPriceGreaterThan(double price);
    List<Product> findByPriceBetween(double minPrice,double maxPrice);
    List<Product> findByPriceLessThan(double price);
}
