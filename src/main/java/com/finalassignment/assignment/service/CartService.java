package com.finalassignment.assignment.service;

import com.finalassignment.assignment.dto.CartDto;
import com.finalassignment.assignment.dto.CartItemDto;

import java.util.List;

public interface CartService {
    CartDto showCartDto(int customerId);

    CartDto addItemToCart(CartItemDto cartItemDto);

    void updateItemInCart(CartItemDto cartItemDto);

    void deleteItemFromCart(int cartDetailId);

    List<CartDto> showAllCart();
}
