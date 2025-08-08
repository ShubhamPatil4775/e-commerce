package com.usk.ecommerce.service;

import com.usk.ecommerce.dto.AddToCartRequest;
import com.usk.ecommerce.dto.CartResponse;
import com.usk.ecommerce.entity.Cart;
import com.usk.ecommerce.entity.CartItem;

public interface CartService {
    Cart getOrCreateCart(Long userId);
    CartItem addProductToCart(Long userId, AddToCartRequest request);
    CartResponse getCartDetails(Long userId);
    CartItem updateCartItemQuantity(Long userId, Long cartItemId, int quantity);
    void removeProductFromCart(Long userId, Long cartItemId);
    void clearCart(Long userID);

}
