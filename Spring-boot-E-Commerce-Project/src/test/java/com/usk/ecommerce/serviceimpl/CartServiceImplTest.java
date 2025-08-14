package com.usk.ecommerce.serviceimpl;

import com.usk.ecommerce.dto.AddToCartRequest;
import com.usk.ecommerce.entity.Cart;
import com.usk.ecommerce.entity.CartItem;
import com.usk.ecommerce.entity.Product;
import com.usk.ecommerce.entity.User;
import com.usk.ecommerce.repository.CartItemRepository;
import com.usk.ecommerce.repository.CartRepository;
import com.usk.ecommerce.repository.ProductRepository;
import com.usk.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
//public class CartServiceImplTest {
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private ProductRepository productRepository;
//
//    @Mock
//    private CartRepository cartRepository;
//
//    @Mock
//    private CartItemRepository cartItemRepository;
//
//    @InjectMocks
//    private CartServiceImpl cartService;
//
//    @Test
//    public void testGetOrCreateCart_CartExists() {
//        Long userId = 1L;
//        User user = new User();
//        Cart existingCart = new Cart(user);
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(existingCart));
//
//        Cart result = cartService.getOrCreateCart(userId);
//
//        assertEquals(existingCart, result);
//        verify(cartRepository, never()).save(any());
//    }
//
//    @Test
//    public void testGetOrCreateCart_CartDoesNotExist() {
//        Long userId = 2L;
//        User user = new User();
//        Cart newCart = new Cart(user);
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        when(cartRepository.findByUserId(userId)).thenReturn(Optional.empty());
//        when(cartRepository.save(any(Cart.class))).thenReturn(newCart);
//
//        Cart result = cartService.getOrCreateCart(userId);
//
//        assertEquals(newCart, result);
//        verify(cartRepository).save(any(Cart.class));
//    }
//
//    @Test
//    public void testGetOrCreateCart_UserNotFound() {
//        Long userId = 3L;
//
//        when(userRepository.findById(userId)).thenReturn(Optional.empty());
//
//        assertThrows(UserNotFoundException.class, () -> cartService.getOrCreateCart(userId));
//        verify(cartRepository, never()).findByUserId(any());
//    }
//
//    @Test
//    public void testAddProductToCart_ProductExistsInCart() {
//        Long userId = 1L;
//        Long productId = 100L;
//        int quantityToAdd = 2;
//
//        User user = new User();
//        Cart cart = new Cart(user);
//        cart.setId(10L);
//
//        Product product = new Product();
//        product.setId(productId);
//        product.setPrice(100.0);
//
//        CartItem existingItem = new CartItem(cart, product, 1, product.getPrice());
//
//        AddToCartRequest request = new AddToCartRequest(productId, quantityToAdd);
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
//        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
//        when(cartItemRepository.findByCartIdAndProductId(cart.getId(), productId))
//                .thenReturn(Optional.of(existingItem));
//        when(cartItemRepository.save(any(CartItem.class))).thenReturn(existingItem);
//
//        CartItem result = cartService.addProductToCart(userId, request);
//
//        assertEquals(3, result.getQuantity()); // 1 existing + 2 added
//        verify(cartItemRepository).save(existingItem);
//    }
//
//    @Test
//    public void testAddProductToCart_ProductNotInCart() {
//        Long userId = 2L;
//        Long productId = 200L;
//        int quantityToAdd = 1;
//
//        User user = new User();
//        Cart cart = new Cart(user);
//        cart.setId(20L);
//
//        Product product = new Product();
//        product.setId(productId);
//        product.setPrice(150.0);
//
//        AddToCartRequest request = new AddToCartRequest(productId, quantityToAdd);
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
//        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
//        when(cartItemRepository.findByCartIdAndProductId(cart.getId(), productId))
//                .thenReturn(Optional.empty());
//
//        CartItem newItem = new CartItem(cart, product, quantityToAdd, product.getPrice());
//        when(cartItemRepository.save(any(CartItem.class))).thenReturn(newItem);
//
//        CartItem result = cartService.addProductToCart(userId, request);
//
//        assertEquals(quantityToAdd, result.getQuantity());
//        assertEquals(product.getPrice(), result.getPriceAtAddToCart());
//        verify(cartItemRepository).save(any(CartItem.class));
//    }
//    @Test
//    public void testAddProductToCart_ProductNotFound() {
//        Long userId = 3L;
//        Long productId = 300L;
//
//        User user = new User();
//        Cart cart = new Cart(user);
//        cart.setId(30L);
//
//        AddToCartRequest request = new AddToCartRequest(productId, 1);
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
//        when(productRepository.findById(productId)).thenReturn(Optional.empty());
//
//        assertThrows(ProductNotFoundException.class, () -> cartService.addProductToCart(userId, request));
//        verify(cartItemRepository, never()).save(any());
//    }
//    @Test
//    public void testGetCartDetails_WithItems() {
//        Long userId = 1L;
//        User user = new User();
//        Cart cart = new Cart(user);
//        cart.setId(10L);
//
//        Product product1 =  new Product("Laptop", "Electronics", 50000.0,1);
//
//        Product product2 =  new Product("Smartphone", "Electronics", 30000.0,1);
//
//        CartItem item1 = new CartItem(cart, product1, 2, 50000.0); // subtotal = 200
//        CartItem item2 = new CartItem(cart, product2, 1, 30000.0); // subtotal = 200
//
//        cart.setCartItems(List.of(item1, item2));
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
//
//        CartResponse response = cartService.getCartDetails(userId);
//
//        assertEquals(2, response.getItems().size());
//        assertEquals(130000.0, response.getTotalAmount());
//        assertEquals(cart.getId(), response.getCartId());
//        assertEquals(userId, response.getUserId());
//    }
//    @Test
//    public void testGetCartDetails_EmptyCart() {
//        Long userId = 2L;
//        User user = new User();
//        Cart cart = new Cart(user);
//        cart.setId(20L);
//        cart.setCartItems(Collections.emptyList());
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
//
//        CartResponse response = cartService.getCartDetails(userId);
//
//        assertEquals(0, response.getItems().size());
//        assertEquals(0.0, response.getTotalAmount());
//    }
//
//    @Test
//    public void testGetCartDetails_UserNotFound() {
//        Long userId = 3L;
//
//        when(userRepository.findById(userId)).thenReturn(Optional.empty());
//
//        assertThrows(UserNotFoundException.class, () -> cartService.getCartDetails(userId));
//    }
//
//    @Test
//    public void testUpdateCartItemQuantity_ValidQuantity() {
//        Long userId = 1L;
//        Long cartItemId = 101L;
//        int newQuantity = 3;
//
//        User user = new User();
//        Cart cart = new Cart(user);
//        cart.setId(10L);
//
//        Product product = new Product("Laptop", "Electronics", 50000.0,1);
//        CartItem cartItem = new CartItem(cart, product, 1, 50000.0);
//        cartItem.setId(cartItemId);
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
//        when(cartItemRepository.findById(cartItemId)).thenReturn(Optional.of(cartItem));
//        when(cartItemRepository.save(cartItem)).thenReturn(cartItem);
//
//        CartItem result = cartService.updateCartItemQuantity(userId, cartItemId, newQuantity);
//
//        assertEquals(newQuantity, result.getQuantity());
//        verify(cartItemRepository).save(cartItem);
//    }
//
//    @Test
//    public void testUpdateCartItemQuantity_InvalidQuantity() {
//        Long userId = 2L;
//        Long cartItemId = 102L;
//        int invalidQuantity = 0;
//
//        assertThrows(IllegalArgumentException.class, () ->
//                cartService.updateCartItemQuantity(userId, cartItemId, invalidQuantity)
//        );
//
//        verify(cartItemRepository, never()).save(any());
//    }
//
//    @Test
//    public void testUpdateCartItemQuantity_CartItemNotFound() {
//        Long userId = 3L;
//        Long cartItemId = 103L;
//        int quantity = 2;
//
//        User user = new User();
//        Cart cart = new Cart(user);
//        cart.setId(30L);
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
//        when(cartItemRepository.findById(cartItemId)).thenReturn(Optional.empty());
//
//        assertThrows(RuntimeException.class, () ->
//                cartService.updateCartItemQuantity(userId, cartItemId, quantity)
//        );
//    }
//
//    @Test
//    public void testRemoveProductFromCart_PositiveScenario() {
//        Long userId = 1L;
//        Long cartItemId = 101L;
//
//        User user = new User();
//        Cart cart = new Cart(user);
//        cart.setId(10L);
//
//        Product product = new Product("Laptop", "Electronics", 50000.0,1);
//        CartItem cartItem = new CartItem(cart, product, 1, 50000.0);
//        cartItem.setId(cartItemId);
//
//        cart.setCartItems(new ArrayList<>(List.of(cartItem)));
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
//        when(cartItemRepository.findById(cartItemId)).thenReturn(Optional.of(cartItem));
//
//        cartService.removeProductFromCart(userId, cartItemId);
//
//        verify(cartItemRepository).delete(cartItem);
//    }
//}

import org.mockito.MockitoAnnotations;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private CartRepository cartRepository;
    @InjectMocks
    private CartServiceImpl cartService;

    private User user;
    private Cart cart;
    private Product product;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        user.setMobileNo(1234567890L);

        cart = new Cart(user);
        cart.setId(1L);
        user.setCart(cart);

        product = new Product();
        product.setId(100L);
        product.setProductName("Laptop");
        product.setPrice(50000.0);
        product.setStockQuantity(10);
        product.setCategory("");
    }

    @Test
    void testGetOrCreateCart_PositiveScenario_CartDoesNotExist() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.empty());

        Cart newCart = new Cart(user);
        when(cartRepository.save(any(Cart.class))).thenReturn(newCart);

        // Act
        Cart result = cartService.getOrCreateCart(1L);

        // Assert
        assertNotNull(result);
        assertEquals(user, result.getUser());
        verify(userRepository).findById(1L);
        verify(cartRepository).findByUserId(1L);
        verify(cartRepository).save(any(Cart.class));
    }
    @Test
    void testAddProductToCart_PositiveScenario_NewItem() {
        // Arrange
        AddToCartRequest request = new AddToCartRequest();
        request.setProductId(100L);
        request.setQuantity(2);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));
        when(productRepository.findById(100L)).thenReturn(Optional.of(product));
        when(cartItemRepository.findByCartIdAndProductId(1L, 100L)).thenReturn(Optional.empty());

        CartItem newCartItem = new CartItem(cart, product, 2, product.getPrice());
        when(cartItemRepository.save(any(CartItem.class))).thenReturn(newCartItem);

        CartItem result = cartService.addProductToCart(1L, request);

        assertNotNull(result);
        assertEquals(2, result.getQuantity());
        assertEquals(product.getPrice(), result.getPriceAtAddToCart());
        assertEquals(product, result.getProduct());
        assertEquals(cart, result.getCart());

        verify(productRepository).save(product);
        verify(cartItemRepository).save(any(CartItem.class));
    }

    @Test
    void testUpdateCartItemQuantity_PositiveScenario() {
        // Arrange
        Long userId = 1L;
        Long cartItemId = 10L;
        int newQuantity = 3;

        Product product = new Product();
        product.setId(100L);
        product.setStockQuantity(10);

        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemId);
        cartItem.setQuantity(1);
        cartItem.setProduct(product);
        cartItem.setCart(cart);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
        when(cartItemRepository.findById(cartItemId)).thenReturn(Optional.of(cartItem));
        when(cartItemRepository.save(any(CartItem.class))).thenReturn(cartItem);

        // Act
        CartItem result = cartService.updateCartItemQuantity(userId, cartItemId, newQuantity);

        // Assert
        assertNotNull(result);
        assertEquals(newQuantity, result.getQuantity());
        verify(cartItemRepository).save(cartItem);
    }
    @Test
    void testRemoveProductFromCart_PositiveScenario() {
        // Arrange
        Long userId = 1L;
        Long cartItemId = 10L;

        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemId);
        cartItem.setCart(cart);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
        when(cartItemRepository.findById(cartItemId)).thenReturn(Optional.of(cartItem));

        // Act
        cartService.removeProductFromCart(userId, cartItemId);

        // Assert
        verify(cartItemRepository).delete(cartItem);
    }
    @Test
    void testClearCart_PositiveScenario() {
        // Arrange
        Long userId = 1L;

        CartItem item1 = new CartItem();
        CartItem item2 = new CartItem();
        cart.getCartItems().add(item1);
        cart.getCartItems().add(item2);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));

        // Act
        cartService.clearCart(userId);

        // Assert
        assertTrue(cart.getCartItems().isEmpty());
        verify(cartRepository).save(cart);
    }

}
