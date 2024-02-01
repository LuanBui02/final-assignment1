package com.finalassignment.assignment.controller;

import com.finalassignment.assignment.dto.CartDto;
import com.finalassignment.assignment.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{customerId}")
    public List<CartDto> getCartByCustomer(@PathVariable int customerId) {
        return cartService.showCart(customerId);
    }

    @PostMapping()
    public void addNewItemToCart(@RequestBody CartDto cartDto) {
        cartService.addItemToCart(cartDto);
    }

    @PutMapping()
    public void updateNewItemInCart() {
        cartService.updateItemInCart();
    }

    @DeleteMapping("/{cartDetailId}")
    public void deleteItemFromCart(@PathVariable int cartDetailId) {
        cartService.deleteItemFromCart(cartDetailId);
    }
}










