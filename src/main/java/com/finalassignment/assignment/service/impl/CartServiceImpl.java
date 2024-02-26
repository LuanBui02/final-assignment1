package com.finalassignment.assignment.service.impl;

import com.finalassignment.assignment.dto.CartDto;
import com.finalassignment.assignment.dto.CartItemDto;
import com.finalassignment.assignment.exception.CartNotFoundByIdException;
import com.finalassignment.assignment.exception.CustomerNotFoundException;
import com.finalassignment.assignment.exception.ItemNotFoundException;
import com.finalassignment.assignment.exception.NotFoundCartDetailByIdException;
import com.finalassignment.assignment.mapper.CartMapper;
import com.finalassignment.assignment.model.Cart;
import com.finalassignment.assignment.model.CartDetail;
import com.finalassignment.assignment.model.Customer;
import com.finalassignment.assignment.model.Item;
import com.finalassignment.assignment.repository.CartDetailRepo;
import com.finalassignment.assignment.repository.CartRepo;
import com.finalassignment.assignment.repository.CustomerRepo;
import com.finalassignment.assignment.repository.ItemRepo;
import com.finalassignment.assignment.service.CartService;
import com.finalassignment.assignment.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private CartDetailRepo cartDetailRepo;
    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
    @Autowired
    private MessageSource messageSource;

    private Cart showCart(int customerId) {
        Optional<Customer> customer = customerRepo.findById(customerId);
        if (customer.isPresent()) {
            Cart cart = null;
            Optional<Cart> cartOptional = cartRepo.findCartByCustomerId(customerId);
            if (cartOptional.isEmpty()) {
                cart = new Cart();
                cart.setCustomer(customer.get());
                cart = cartRepo.save(cart);
            } else {
                cart = cartOptional.get();
            }
            return cart;
        } else {
            return null;
        }

    }

    private void checkCustomerEmpty(int customerId) {
        Optional<Customer> customerOptional = customerRepo.findById(customerId);
        if (customerOptional.isEmpty()) {
            logger.error("CustomerNotFound: {}", Constant.customerNotFound);
            throw new CustomerNotFoundException(customerId);
        }
    }

    private void checkItemEmpty(int itemId) {
        Optional<Item> itemOptional = itemRepo.findById(itemId);
        if (itemOptional.isEmpty()) {
            logger.error("ItemNotFound: {}", Constant.itemNotFound);
            throw new ItemNotFoundException();
        }
    }

    private void checkCartDetailEmpty(int cartDetailId) {
        Optional<CartDetail> cartDetailOptional = cartDetailRepo.findById(cartDetailId);
        if (cartDetailOptional.isEmpty()) {
            logger.error("CartDetailNotFound: {}", Constant.cartDetailNotFound);
            throw new NotFoundCartDetailByIdException();
        }
    }

    @Override
    public CartDto showCartDto(int customerId) {
        Optional<Cart> cartOptional = cartRepo.findCartByCustomerId(customerId);
        if (cartOptional.isEmpty()) {
            logger.error("CartNotFoundById: {}", Constant.finalCartNotFoundById);
            throw new CartNotFoundByIdException(customerId);
        }
        logger.info("CartFound: {}", Constant.finalCartFound);
        return CartMapper.INSTANCE.toDto(showCart(customerId));
    }

    @Override
    public CartDto addItemToCart(CartItemDto cartItemDto) {
        Cart cart = showCart(cartItemDto.getCustomerId());

        Optional<Item> itemOptional = itemRepo.findById(cartItemDto.getItemId());

        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            CartDetail cartDetail = new CartDetail();
            cartDetail.setItem(item);
            cartDetail.setCart(cart);
            cartDetail.setQuantity(cartItemDto.getQuantity());
            cartDetail.setDateAdded(new Date());
            cartDetailRepo.save(cartDetail);

        } else {
            checkItemEmpty(cartItemDto.getItemId());
        }
        checkCustomerEmpty(cartItemDto.getCustomerId());
        logger.info("CartAdded: {}", Constant.cartAdded);
        return CartMapper.INSTANCE.toDto(showCart(cartItemDto.getCustomerId()));
    }

    @Override
    public void updateItemInCart(CartItemDto cartItemDto) {
        Optional<CartDetail> cartDetailOptional = cartDetailRepo.findById(cartItemDto.getId());
        if (cartDetailOptional.isPresent()) {
            Optional<Item> itemOptional = itemRepo.findById(cartItemDto.getItemId());

            if (itemOptional.isPresent()) {
                CartDetail cartDetail = cartDetailOptional.get();
                Item item = itemOptional.get();
                Optional<Customer> customer = customerRepo.findById(cartItemDto.getCustomerId());

                if ((customer.isPresent()) && (customer.get().getId() == cartItemDto.getCustomerId())) {
                    cartDetail.setItem(item);
                    cartDetail.setQuantity(cartItemDto.getQuantity());
                    cartDetail.setDateAdded(new Date());
                    cartDetailRepo.save(cartDetail);
                } else {
                    checkCustomerEmpty(cartItemDto.getCustomerId());
                }
                logger.info("ItemUpdated: {}", Constant.itemUpdated);
            } else {
                checkItemEmpty(cartItemDto.getItemId());
            }
        } else {
            checkCartDetailEmpty(cartItemDto.getId());
        }
    }

    @Override
    public void deleteItemFromCart(int cartDetailId) {
        checkCartDetailEmpty(cartDetailId);
        logger.info("ItemDeleted: {}", Constant.itemDeleted);
        cartDetailRepo.deleteById(cartDetailId);
    }

    @Override
    public List<CartDto> showAllCart() {
        List<Cart> list = cartRepo.findAll();
        return list.stream().map(CartMapper.INSTANCE::toDto).collect(Collectors.toList());
    }
}