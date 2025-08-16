package com.usk.ecommerce.serviceimpl;

import com.usk.ecommerce.dto.AddToCartRequest;
import com.usk.ecommerce.dto.CartItemResponse;
import com.usk.ecommerce.dto.CartResponse;
import com.usk.ecommerce.entity.Cart;
import com.usk.ecommerce.entity.CartItem;
import com.usk.ecommerce.entity.Product;
import com.usk.ecommerce.entity.User;
import com.usk.ecommerce.exception.CartItemNotFoundException;
import com.usk.ecommerce.exception.ProductNotFoundException;
import com.usk.ecommerce.exception.UserNotFoundException;
import com.usk.ecommerce.repository.CartItemRepository;
import com.usk.ecommerce.repository.CartRepository;
import com.usk.ecommerce.repository.ProductRepository;
import com.usk.ecommerce.repository.UserRepository;
import com.usk.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart getOrCreateCart(Long userId) {
        User user = userRepository.findById(userId).
                orElseThrow(() ->new UserNotFoundException("user not with ID: "+userId));

        return cartRepository.findByUserId(userId)
                .orElseGet(()->
                {
                    Cart cart= new Cart(user);
                    user.setCart(cart);
                    return cartRepository.save(cart);
                });
    }

    @Override
    public CartItem addProductToCart(Long userId, AddToCartRequest request) {
        Cart cart = getOrCreateCart(userId);
        Product product = productRepository.findById(request.getProductId()).orElseThrow(()->
                new ProductNotFoundException("Product not found with ID"));
        Optional<CartItem> existingCartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(),request.getProductId());
        CartItem cartItem;
        if (existingCartItem.isPresent()){
            cartItem = existingCartItem.get();
            cartItem.setQuantity((cartItem.getQuantity()+ request.getQuantity()));
        }else {
            cartItem = new CartItem(cart,product,request.getQuantity(),product.getPrice());
            cart.addCartItem(cartItem);
        }
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartResponse getCartDetails(Long userId) {
    Cart cart = getOrCreateCart(userId);
        List<CartItemResponse> itemResponses = cart.getCartItems().stream()
                .map(item ->
                        new CartItemResponse(
                                item.getId(),
                                item.getProduct().getId(),
                                item.getProduct().getProductName(),
                                item.getProduct().getPrice(),
                                item.getPriceAtAddToCart(),
                                item.getQuantity(),
                                (item.getProduct().getPrice()* item.getQuantity())
                        )).collect(Collectors.toList());

       Double totalAmount= itemResponses.stream().mapToDouble(CartItemResponse::getSubTotal).sum();
        return new CartResponse(cart.getId(),userId,itemResponses,totalAmount);
    }

    @Override
    public CartItem updateCartItemQuantity(Long userId, Long cartItemId, int quantity) {
        if (quantity <=0){
            throw  new IllegalArgumentException("new quantity must be positive");
        }
        Cart cart = getOrCreateCart(userId);
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(()->
                new CartItemNotFoundException("Cart item not found with ID"));
        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void removeProductFromCart(Long userId, Long cartItemId) {
        Cart cart = getOrCreateCart(userId);
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(()->
                new CartItemNotFoundException("Cart item not found with ID")
        );

        if(!cartItem.getCart().getId().equals(cart.getId())){
            throw new IllegalArgumentException("Cart item does belong to user");
        }
        cart.removeCartItem(cartItem);
        cartItemRepository.delete(cartItem);
    }

    @Override
    public void clearCart(Long userID) {
        Cart cart = getOrCreateCart(userID);
        cart.getCartItems().clear();
        cartRepository.save(cart);
    }
}
