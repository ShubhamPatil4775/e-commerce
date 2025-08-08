package com.usk.ecommerce.controller;

import com.usk.ecommerce.dto.ProductDTO;
import com.usk.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.saveProduct(productDTO), HttpStatus.CREATED);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }

    @GetMapping("/search/getByName/{productName}")
    public ResponseEntity<?> getByName(@PathVariable String productName){
        return new ResponseEntity<>(productService.searchByProductName(productName),HttpStatus.OK);
    }

    @GetMapping("/search/getByCategory/{category}")
    public ResponseEntity<?> getByCategory(@PathVariable String category){
        return new ResponseEntity<>(productService.searchByProductCategory(category),HttpStatus.OK);
    }

    @GetMapping("/search/getByInPriceRange/{minPrice}/{maxPrice}")
    public ResponseEntity<?> getByInPriceRange(@PathVariable double minPrice,@PathVariable double maxPrice){
        return new ResponseEntity<>(productService.searchProductInPriceRange(minPrice,maxPrice),HttpStatus.OK);
    }

    @GetMapping("/search/getProductsByLessThanPrice/{price}")
    public ResponseEntity<?> getProductsByLessThanPrice(@PathVariable double price){
        return new ResponseEntity<>(productService.searchProductLessThanPrice(price),HttpStatus.OK);
    }

    @GetMapping("/search/getProductsByGreaterThanPrice/{maxPrice}")
    public ResponseEntity<?> getProductsByGreaterThanPrice(@PathVariable double maxPrice){
        return new ResponseEntity<>(productService.searchProductGreaterThanPrice(maxPrice),HttpStatus.OK);
    }
}
