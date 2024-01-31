package com.finalassignment.assignment.controller;

import com.finalassignment.assignment.mapper.CartDetailMapper;
import com.finalassignment.assignment.mapper.CartMapper;
import com.finalassignment.assignment.mapper.CustomerMapper;
import com.finalassignment.assignment.model.Cart;
import com.finalassignment.assignment.model.CartDetail;
import com.finalassignment.assignment.model.Customer;
import com.finalassignment.assignment.repository.CartDetailRepo;
import com.finalassignment.assignment.repository.CartRepo;
import com.finalassignment.assignment.repository.CustomerRepo;
import org.mapstruct.factory.Mappers;
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
import java.util.Optional;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private CartDetailRepo cartDetailRepo;
    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
    private final CartDetailMapper cartDetailMapper = Mappers.getMapper(CartDetailMapper.class);
    private final CartMapper cartMapper = Mappers.getMapper(CartMapper.class);

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

    @PostMapping()
    public Cart addItemInCart(@RequestBody Cart cart) {
//      I want to get customer already created in cart and find it in data body
        Customer customer = customerRepo.findById(cart.getCustomer().getId()).get();
        cart.setCustomer(customer);
//      The part that i don't know how to do
//      Set cartDetail in customer...
//      Set item in cartDetail...

        return cartRepo.save(cart);

//        CustomerDto customer = cartDto.getCustomerDto();
//        Customer modelCustomer = customerMapper.toModel(customer);
//
//        cartDto.setCustomerDto(customer);
//        Cart modelCart = cartMapper.toModel(cartDto);
//
//        customer.setId(modelCart.getCustomer().getId());
//        customer.setUsername(modelCart.getCustomer().getUsername());
//        customer.setPassword(modelCart.getCustomer().getPassword());
//
//        modelCart.setCustomer(modelCustomer);
//        return cartRepo.save(modelCart);

    }

    @PutMapping()
    public void updateItem() {

    }

    @DeleteMapping("/{cartDetailId}")
    public void deleteItem(@PathVariable int cartDetailId) {

    }
}










