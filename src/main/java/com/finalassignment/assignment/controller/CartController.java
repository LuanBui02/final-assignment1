package com.finalassignment.assignment.controller;

import com.finalassignment.assignment.model.Cart;
import com.finalassignment.assignment.model.CartDetail;
import com.finalassignment.assignment.model.Customer;
import com.finalassignment.assignment.model.Item;
import com.finalassignment.assignment.repository.CartDetailRepo;
import com.finalassignment.assignment.repository.CartRepo;
import com.finalassignment.assignment.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private CartDetailRepo cartDetailRepo;

    @GetMapping("/{customerId}")
    public Optional<Cart> getCartByCustomer(@PathVariable int customerId) {
        return cartRepo.findById(customerId);
    }

    @GetMapping("/cartDetail")
    public List<CartDetail> getCartDetail() {
        return cartDetailRepo.findAll();
    }

    @GetMapping()
    public List<Cart> getAllCarts() {
        return cartRepo.findAll();
    }

    @GetMapping("/customer")
    public List<Customer> getCustomer() {
        return customerRepo.findAll();
    }

    @PostMapping("/customer")
    public void addCustomer(@RequestBody Customer customer) {
        customerRepo.save(customer);
    }

    @PostMapping("/cartDetail")
    public void addCartDetail(@RequestBody CartDetail cartDetail) {
        cartDetailRepo.save(cartDetail);
    }

    @PostMapping()
    public CartDetail addItemInCart(@RequestBody CartDetail cartDetail, Customer customer, Item item) {

        Cart cart = new Cart();

        Customer newCustomer = new Customer();
        newCustomer.setId(customer.getId());
        newCustomer.setUsername(customer.getUsername());
        cart.setCustomer(newCustomer);
        cartDetail.setCart(cart);


        Item newItem = new Item();

        newItem.setId(item.getId());

        cartDetail.setItem(newItem);


        return cartDetailRepo.save(cartDetail);
    }

}
