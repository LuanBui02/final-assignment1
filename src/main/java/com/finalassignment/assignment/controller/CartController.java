package com.finalassignment.assignment.controller;

import com.finalassignment.assignment.model.Cart;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @GetMapping("/{customerId}")
    public void getCartByCustomer(@PathVariable int customerId) {

    }

    @PostMapping()
    public void addItemToCart(@RequestBody Cart cart) {

    }

    @PutMapping()
    public void updateItemInCart() {

    }

    @DeleteMapping("/{cartDetailId}")
    public void deleteItemFromCart(@PathVariable int cartDetailId) {

    }
}










