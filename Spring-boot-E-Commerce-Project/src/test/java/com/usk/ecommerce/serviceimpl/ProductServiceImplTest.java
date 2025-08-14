package com.usk.ecommerce.serviceimpl;
import com.usk.ecommerce.dto.ProductDTO;
import com.usk.ecommerce.entity.Product;
import com.usk.ecommerce.exception.ProductAlreadyExistsException;
import com.usk.ecommerce.exception.ProductNotFoundException;
import com.usk.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

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
        when(productRepository.findByProductNameIgnoreCase("Laptop")).thenReturn(Optional.empty());
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product savedProduct = productService.saveProduct(productDTO);

        assertNotNull(savedProduct);
        assertEquals("Laptop", savedProduct.getProductName());
        verify(productRepository).findByProductNameIgnoreCase("Laptop");
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void testSaveProduct_AlreadyExists() {
        when(productRepository.findByProductNameIgnoreCase("Laptop")).thenReturn(Optional.of(product));

        assertThrows(ProductAlreadyExistsException.class, () -> productService.saveProduct(productDTO));
        verify(productRepository).findByProductNameIgnoreCase("Laptop");
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = List.of(product);
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertEquals(1, result.size());
        assertEquals("Laptop", result.get(0).getProductName());
        verify(productRepository).findAll();
    }

    @Test
    void testSearchByProductName_Found() {
        when(productRepository.findByProductNameIgnoreCase("Laptop")).thenReturn(Optional.of(product));

        Product result = productService.searchByProductName("Laptop");

        assertNotNull(result);
        assertEquals("Laptop", result.getProductName());
        verify(productRepository).findByProductNameIgnoreCase("Laptop");
    }

    @Test
    void testSearchByProductName_NotFound() {
        when(productRepository.findByProductNameIgnoreCase("Laptop"))
                .thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.searchByProductName("Laptop"));

        verify(productRepository).findByProductNameIgnoreCase("Laptop");
    }

}

