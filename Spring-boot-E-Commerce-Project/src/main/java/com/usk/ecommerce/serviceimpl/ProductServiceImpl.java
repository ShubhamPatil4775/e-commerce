package com.usk.ecommerce.serviceimpl;

import com.usk.ecommerce.dto.ProductDTO;
import com.usk.ecommerce.entity.Product;
import com.usk.ecommerce.entity.User;
import com.usk.ecommerce.exception.ProductAlreadyExistsException;
import com.usk.ecommerce.repository.ProductRepository;
import com.usk.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;



    @Override
    public Product saveProduct(ProductDTO productDTO) {
      Optional<Product> product = productRepository.findByProductName(productDTO.getProductName());
      if (product.isPresent()){
          throw new ProductAlreadyExistsException("Product already exists");
      }
      Product product1 = new Product(
              productDTO.getProductName(),
              productDTO.getCategory(),
              productDTO.getPrice(),
              productDTO.getStockQuantity()
      );
         return productRepository.save(product1);
    }

    @Override
    public void saveProducts(ProductDTO productDTO) {
        List<Product> products= Product.storeProducts();
        productRepository.saveAll(products);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product searchByProductName(String productName) {
        Product product = productRepository.findByProductName(productName).orElse(null);
        return product;
    }

    @Override
    public List<Product> searchByProductCategory(String Category) {
        return productRepository.findByCategoryIgnoreCase(Category);
    }

    @Override
    public List<Product> searchProductInPriceRange(double price1, double price2) {
        return productRepository.findByPriceBetween(price1,price2);
    }

    @Override
    public List<Product> searchProductLessThanPrice(double price) {
        return productRepository.findByPriceLessThan(price);
    }

    @Override
    public List<Product> searchProductGreaterThanPrice(double price) {
        return productRepository.findByPriceGreaterThan(price);
    }
}
