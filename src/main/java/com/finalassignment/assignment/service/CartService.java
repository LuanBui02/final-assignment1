package com.finalassignment.assignment.service;

import com.finalassignment.assignment.dto.CartDto;

import java.util.List;

public interface CartService {
    List<CartDto> showCart(int customerId);

    void addItemToCart(CartDto cartDto);

    void updateItemInCart();

    void deleteItemFromCart(int cartDetailId);
}
