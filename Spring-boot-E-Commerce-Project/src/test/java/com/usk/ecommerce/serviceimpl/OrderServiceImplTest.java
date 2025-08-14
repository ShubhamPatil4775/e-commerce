package com.usk.ecommerce.serviceimpl;

import com.usk.ecommerce.dto.*;
import com.usk.ecommerce.entity.*;
import com.usk.ecommerce.repository.CartRepository;
import com.usk.ecommerce.repository.OrderRepository;
import com.usk.ecommerce.repository.UserRepository;
import com.usk.ecommerce.service.BankAccountService;
import com.usk.ecommerce.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private BankAccountService bankAccountService;

    @Mock
    private CartService cartService;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private long userId;
    private String accountNo;
    private User user;
    private Product product;
    private CartItem cartItem;
    private Cart cart;
    private CartItemResponse cartItemResponse;
    private BankDebitCreditResponse bankResponse;
    private CartResponse cartResponse;
    private PlaceOrderRequest request;
    private Order order;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userId = 1L;
        accountNo = "1010101010";

        user = new User();
        user.setId(userId);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("securePass");
        user.setMobileNo(9876543210L);

        product = new Product();
        product.setId(100L);
        product.setProductName("Laptop");
        product.setPrice(50000.0);
        product.setStockQuantity(10);

        cartItem = new CartItem();
        cartItem.setId(200L);
        cartItem.setProduct(product);
        cartItem.setQuantity(2);
        cartItem.setPriceAtAddToCart(product.getPrice());

        cart = new Cart(user);
        cart.setId(300L);
        cart.getCartItems().add(cartItem);

        cartItemResponse = new CartItemResponse();
        cartItemResponse.setCartItemId(cartItem.getId());

        cartResponse = new CartResponse();
        cartResponse.setCartId(cart.getId());
        cartResponse.setUserId(userId);
        cartResponse.setItems(List.of(cartItemResponse));
        cartResponse.setTotalAmount(100000.0);

        bankResponse = new BankDebitCreditResponse();
        bankResponse.setSuccess(true);
        bankResponse.setTransactionId("10101010");

        request = new PlaceOrderRequest();
        request.setAccountNo(accountNo);
        request.setDeliveryAddress("123 Street");
        request.setDeliveryContactNo("9876543210");
        request.setPaymentMethod("Bank");

        order = new Order();
        order.setId(400L);
        order.setUser(user);
        order.setAmount(cartResponse.getTotalAmount());
        order.setOrderStatus(OrderStatus.DONE);
        order.setTransactionId(bankResponse.getTransactionId());
    }
//    @Test
//    void testPlaceOrder_PositiveScenario() {
//        // Arrange
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        when(cartService.getCartDetails(userId)).thenReturn(cartResponse);
//        when(bankAccountService.debit(accountNo, cartResponse.getTotalAmount())).thenReturn(bankResponse);
//        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
//        when(orderRepository.save(any(Order.class))).thenReturn(order);
//
//        // Act
//        OrderResponse response = orderService.placeOrder(userId, request);
//
//        // Assert
//        assertNotNull(response);
//        assertEquals(OrderStatus.DONE, response.getOrderStatus());
//        assertEquals(userId, response.getUserId());
//        assertEquals("10101010", response.getTransactionId());
//
//        verify(userRepository).findById(userId);
//        verify(cartService).getCartDetails(userId);
//        verify(bankAccountService).debit(accountNo, cartResponse.getTotalAmount());
//        verify(orderRepository).save(any(Order.class));
//        verify(cartService).clearCart(userId);
//    }
    @Test
    void testGetOrderByUserId_PositiveScenario() {
        // Arrange
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(orderRepository.findByUser(user)).thenReturn(List.of(order));

        // Act
        List<OrderResponse> responses = orderService.getOrderByUserId(userId);

        // Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals(order.getTransactionId(), responses.get(0).getTransactionId());
        assertEquals(order.getAmount(), responses.get(0).getTotalAmount());

        verify(userRepository).findById(userId);
        verify(orderRepository).findByUser(user);
    }
    @Test
    void testGetOrderById_PositiveScenario() {
        // Arrange
        Long orderId = order.getId();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(orderRepository.findByIdAndUser(orderId, user)).thenReturn(Optional.of(order));

        // Act
        OrderResponse response = orderService.getOrderById(userId, orderId);

        // Assert
        assertNotNull(response);
        assertEquals(orderId, response.getOrderId());
        assertEquals(userId, response.getUserId());
        assertEquals(order.getTransactionId(), response.getTransactionId());

        verify(userRepository).findById(userId);
        verify(orderRepository).findByIdAndUser(orderId, user);
    }

}

