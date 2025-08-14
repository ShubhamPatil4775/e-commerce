package com.usk.ecommerce.controller;

import com.usk.ecommerce.dto.AddToCartRequest;
import com.usk.ecommerce.dto.CartResponse;
import com.usk.ecommerce.service.CartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartControllerTest {

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    @Test
    public void testAddProductToCart_AddedProductToCart() {

        Long userId = 1L;
        AddToCartRequest request = new AddToCartRequest(10L, 2);

        ResponseEntity<?> response = cartController.addProductToCart(userId, request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("product added successfully", response.getBody());

        verify(cartService).addProductToCart(userId, request);
    }

    @Test
    public void testGetCartDetails_getCartDetails() {
        Long userId = 1L;
        CartResponse mockResponse = new CartResponse(10L, userId, List.of(), 0.0);
        when(cartService.getCartDetails(userId)).thenReturn(mockResponse);

        ResponseEntity<CartResponse> response = cartController.getCartDetails(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        verify(cartService).getCartDetails(userId);
    }

    @Test
    public void shouldUpdateCartItemQuantitySuccessfully() {
        Long userId = 1L;
        Long cartItemId = 101L;
        int quantity = 3;

        ResponseEntity<?> response = cartController.updateCartItemQuantity(userId, cartItemId, quantity);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("updated the cartItem", response.getBody());

        verify(cartService).updateCartItemQuantity(userId, cartItemId, quantity);
    }


    @Test
    public void shouldRemoveProductFromCartSuccessfully() {
        Long userId = 1L;
        Long cartItemId = 101L;

        ResponseEntity<?> response = cartController.removeProductFromCart(userId, cartItemId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully deleted", response.getBody());

        verify(cartService).removeProductFromCart(userId, cartItemId);
    }


    @Test
    public void shouldClearCartSuccessfully() {
        Long userId = 1L;

        ResponseEntity<String> response = cartController.clearCart(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully cleared the cart", response.getBody());

        verify(cartService).clearCart(userId);
    }

}
