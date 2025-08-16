package com.usk.ecommerce.serviceimpl;

import com.usk.ecommerce.dto.*;
import com.usk.ecommerce.entity.*;
import com.usk.ecommerce.exception.AccountNumberRequiredException;
import com.usk.ecommerce.exception.UserNotFoundException;
import com.usk.ecommerce.repository.CartRepository;
import com.usk.ecommerce.repository.OrderItemRepository;
import com.usk.ecommerce.repository.OrderRepository;
import com.usk.ecommerce.repository.UserRepository;
import com.usk.ecommerce.service.BankAccountService;
import com.usk.ecommerce.service.CartService;
import com.usk.ecommerce.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BankAccountService bankAccountService;
    @Autowired
    private CartService cartService;

    @Override
    public OrderResponse placeOrder(Long userId, PlaceOrderRequest request) {
        User user= userRepository.findById(userId).orElseThrow(()->
                new UserNotFoundException("user not found with id"));
        String accountNo = request.getAccountNo();
        if (accountNo ==null || accountNo.isEmpty()){
            throw new AccountNumberRequiredException("Account no is required to place an order");
        }
        CartResponse cartDetails= cartService.getCartDetails(userId);
        if (cartDetails.getItems().isEmpty()){
            throw new IllegalArgumentException("Cannot place an order with empty cart");
        }
        double totalAmount= cartDetails.getTotalAmount();
        BankDebitCreditResponse bankDebitCreditResponse= bankAccountService.debit(accountNo,totalAmount);

        if (!bankDebitCreditResponse.isSuccess()){
            throw new RuntimeException("Payment failed: "+bankDebitCreditResponse.getMessage());
        }
        Order order = new Order(
                user,
                totalAmount,
                request.getDeliveryAddress(),
                request.getDeliveryContactNo(),
                request.getPaymentMethod(),
                bankDebitCreditResponse.getTransactionId()
        );
        order.setOrderStatus(OrderStatus.PROCESSING);

         Cart cart= cartRepository.findById(cartDetails.getCartId()).orElseThrow(()->
                 new RuntimeException("Cart not found"));
         for (CartItemResponse cartItemResponse:cartDetails.getItems()){
             CartItem cartItem= cart.getCartItems().stream()
                     .filter(ci->ci.getId().equals(cartItemResponse.getCartItemId()))
                     .findFirst()
                     .orElseThrow(()->new RuntimeException("Cart item not found"));

             cartItem.getProduct().decreaseStock(cartItem.getQuantity());
             OrderItem orderItem= new OrderItem(
                     order,
                     cartItem.getProduct(),
                     cartItem.getQuantity(),
                     cartItem.getPriceAtAddToCart()
             );
             order.addOrderItem(orderItem);
         }
         Order order1= orderRepository.save(order);

         cartService.clearCart(userId);
        return mapToOrderResponse(order1);
    }

    @Override
    public List<OrderResponse> getOrderByUserId(Long userId) {
        User user= userRepository.findById(userId).orElseThrow(()->
                new UserNotFoundException("user not found with id"));
        List<Order> orders = orderRepository.findByUser(user);
        return orders.stream()
                .map(this::mapToOrderResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse getOrderById(Long userId, Long orderId) {
        User user= userRepository.findById(userId).orElseThrow(()->
                new UserNotFoundException("user not found with id"));
        Order order = orderRepository.findByIdAndUser(orderId,user).orElseThrow(()->
                new RuntimeException("Order does not or does not belong to user "));
        return mapToOrderResponse(order);
    }

    public OrderResponse mapToOrderResponse(Order order){
        List<OrderItemResponse> itemResponses = order.getOrderItems()
                .stream()
                .map(item-> new OrderItemResponse(
                     item.getId(),
                     item.getProduct().getId(),
                     item.getProduct().getProductName(),
                     item.getPurchasePrice(),
                     item.getQuantity(),
                     item.getPurchasePrice()*item.getQuantity()
                )).collect(Collectors.toList());
                return new OrderResponse(
                        order.getId(),
                        order.getUser().getId(),
                        order.getOrderDate(),
                        order.getAmount(),
                        order.getOrderStatus(),
                        order.getAddress(),
                        order.getContactNo(),
                        order.getPaymentMode(),
                        order.getTransactionId(),
                        itemResponses
                );
    }

}
