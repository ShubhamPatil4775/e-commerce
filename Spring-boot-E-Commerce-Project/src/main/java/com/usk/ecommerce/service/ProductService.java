package com.usk.ecommerce.service;

import com.usk.ecommerce.dto.ProductDTO;
import com.usk.ecommerce.entity.Product;

import javax.swing.*;
import java.util.List;

public interface ProductService {
    Product saveProduct(ProductDTO productDTO);
    void saveProducts(ProductDTO productDTO);

    List<Product> getAllProducts();

    Product searchByProductName(String productName);

    List<Product> searchByProductCategory(String Category);

    List<Product> searchProductInPriceRange(double price1,double price2);

    List<Product> searchProductLessThanPrice(double price);
    List<Product> searchProductGreaterThanPrice(double price);

}
