package com.finalassignment.assignment.controller;

import com.finalassignment.assignment.dto.CartDto;
import com.finalassignment.assignment.dto.CartItemDto;
import com.finalassignment.assignment.service.CartService;
import com.finalassignment.assignment.service.impl.CartServiceImpl;
import com.finalassignment.assignment.util.AbstractMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CartController extends  AbstractMessage{
    @Autowired
    private CartService cartService;
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
    @GetMapping("/{customerId}")
    public CartDto getCartByCustomer(@PathVariable int customerId) {
        logger.info("StartToFindCart: {}", getMessage("StartToFindCart"));
        return cartService.showCartDto(customerId);
    }

    @PostMapping()
    public CartDto addNewItemToCart(@RequestBody CartItemDto cartItemDto) {
        logger.info("StartToAddCart: {}", getMessage("StartToAddCart"));
        return cartService.addItemToCart(cartItemDto);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNewItemInCart(@RequestBody CartItemDto cartItemDto) {
        logger.info("StartToUpdateCart: {}", getMessage("StartToUpdateCart"));
        cartService.updateItemInCart(cartItemDto);
    }

    @DeleteMapping("/{cartDetailId}")
    public void deleteItemFromCart(@PathVariable int cartDetailId) {
        logger.info("StartToDeleteCart: {}", getMessage("StartToDeleteCart"));
        cartService.deleteItemFromCart(cartDetailId);
    }
}










