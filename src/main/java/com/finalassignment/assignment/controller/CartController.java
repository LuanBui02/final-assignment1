package com.finalassignment.assignment.controller;

import com.finalassignment.assignment.dto.CartDto;
import com.finalassignment.assignment.dto.CartItemDto;
import com.finalassignment.assignment.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{customerId}")
    public CartDto getCartByCustomer(@PathVariable int customerId) {
        return cartService.showCartDto(customerId);
    }

    @PostMapping()
    public CartDto addNewItemToCart(@RequestBody CartItemDto cartItemDto) {
        return cartService.addItemToCart(cartItemDto);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNewItemInCart(@RequestBody CartItemDto cartItemDto) {
        cartService.updateItemInCart(cartItemDto);
    }

    @DeleteMapping("/{cartDetailId}")
    public void deleteItemFromCart(@PathVariable int cartDetailId) {
        cartService.deleteItemFromCart(cartDetailId);
    }
}










