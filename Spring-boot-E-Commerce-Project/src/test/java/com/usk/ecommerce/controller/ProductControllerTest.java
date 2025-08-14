package com.usk.ecommerce.controller;
import com.usk.ecommerce.dto.ProductDTO;
import com.usk.ecommerce.entity.Product;
import com.usk.ecommerce.exception.ProductAlreadyExistsException;
import com.usk.ecommerce.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private ProductDTO productDTO;
    private Product product;

    @BeforeEach
    void setUp() {
        productDTO = new ProductDTO();
        productDTO.setProductName("Laptop");
        productDTO.setCategory("Electronics");
        productDTO.setPrice(75000.0);
        productDTO.setStockQuantity(10);

        product = new Product();
        product.setId(1L);
        product.setProductName("Laptop");
        product.setCategory("Electronics");
        product.setPrice(75000.0);
        product.setStockQuantity(10);
    }

    @Test
    void testSaveProduct_Success() {
        when(productService.saveProduct(productDTO)).thenReturn(product);

        ResponseEntity<?> responseEntity = productController.saveProduct(productDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
        verify(productService).saveProduct(productDTO);
    }

    @Test
    void testSaveProduct_AlreadyExists() {
        when(productService.saveProduct(productDTO)).thenThrow(new ProductAlreadyExistsException("Product already exists"));

        assertThrows(ProductAlreadyExistsException.class, () -> productController.saveProduct(productDTO));
        verify(productService).saveProduct(productDTO);
    }

    @Test
    void testGetAllProducts_Success() {
        List<Product> products = List.of(product);
        when(productService.getAllProducts()).thenReturn(products);

        ResponseEntity<?> responseEntity = productController.getAllProducts();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(products, responseEntity.getBody());
        verify(productService).getAllProducts();
    }

    @Test
    void testSearchByProductName_Success() {
        when(productService.searchByProductName("Laptop")).thenReturn(product);

        ResponseEntity<?> responseEntity = productController.getByName("Laptop");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
        verify(productService).searchByProductName("Laptop");
    }
}

