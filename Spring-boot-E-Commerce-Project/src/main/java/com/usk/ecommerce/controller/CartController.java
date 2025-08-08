package com.usk.ecommerce.controller;

import com.usk.ecommerce.dto.AddToCartRequest;
import com.usk.ecommerce.dto.CartResponse;
import com.usk.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v3/{userId}/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/item")
    public ResponseEntity<?> addProductToCart(@PathVariable Long userId, @RequestBody AddToCartRequest request){
          cartService.addProductToCart(userId,request);
        return new ResponseEntity<>("product added successfully ", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CartResponse> getCartDetails(@PathVariable Long userId){
        return new ResponseEntity<>(cartService.getCartDetails(userId),HttpStatus.OK);
    }

    @PutMapping("/items/{cartItemId}")
    public ResponseEntity<?> updateCartItemQuantity(@PathVariable Long userId, @PathVariable Long cartItemId, @RequestParam int quantity){
        cartService.updateCartItemQuantity(userId,cartItemId,quantity);
        return new ResponseEntity<>("updated the cartItem",HttpStatus.OK);
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<?> removeProductFromCart(@PathVariable Long userId,@PathVariable Long cartItemId){
        cartService.removeProductFromCart(userId,cartItemId);
        return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> clearCart(@PathVariable Long userId){
        cartService.clearCart(userId);
        return new ResponseEntity<>("Successfully cleared the cart",HttpStatus.OK);
    }
}
