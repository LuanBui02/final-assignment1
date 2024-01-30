package com.finalassignment.assignment.controller;

import com.finalassignment.assignment.dto.AddCartDto;
import com.finalassignment.assignment.dto.CartDetailDto;
import com.finalassignment.assignment.dto.CartDto;
import com.finalassignment.assignment.dto.CustomerDto;
import com.finalassignment.assignment.dto.ItemDto;
import com.finalassignment.assignment.mapper.CartDetailMapper;
import com.finalassignment.assignment.mapper.CartMapper;
import com.finalassignment.assignment.model.Cart;
import com.finalassignment.assignment.model.CartDetail;
import com.finalassignment.assignment.model.Customer;
import com.finalassignment.assignment.repository.CartDetailRepo;
import com.finalassignment.assignment.repository.CartRepo;
import com.finalassignment.assignment.repository.CustomerRepo;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public CartDetail addItemInCart(@RequestBody CartDetailDto cartDetailDto) {
        CartDto cartDto = new CartDto();

        CustomerDto customerDto = new CustomerDto();
        cartDto.setId(customerDto.getId());

        cartDto.setCustomerDto(customerDto);
        cartDetailDto.setCartDto(cartDto);

        ItemDto itemDto = new ItemDto();
        cartDetailDto.setItemDto(itemDto);
        CartDetail model = cartDetailMapper.toModel(cartDetailDto);
        return cartDetailRepo.save(model);

    }


}
