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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Locale;
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

    @Override
    public CartDto showCartDto(int customerId) {
        Optional<Cart> cartOptional = cartRepo.findCartByCustomerId(customerId);
        if (cartOptional.isEmpty()) {
            String cartNotFoundById = messageSource.getMessage("CartNotFoundById", null, Locale.ENGLISH);
            logger.error("CartNotFoundById: {}", cartNotFoundById);
            throw new CartNotFoundByIdException(customerId);
        }
        String cartFound = messageSource.getMessage("CartFound", null, Locale.ENGLISH);
        logger.info("CartFound: {}", cartFound);
        return CartMapper.INSTANCE.toDto(showCart(customerId));
    }

    @Override
    public CartDto addItemToCart(CartItemDto cartItemDto) {
        Cart cart = showCart(cartItemDto.getCustomerId());

        Optional<Item> itemOptional = itemRepo.findById(cartItemDto.getItemId());

        Optional<Customer> customerOptional = customerRepo.findById(cartItemDto.getCustomerId());
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            CartDetail cartDetail = new CartDetail();
            cartDetail.setItem(item);
            cartDetail.setCart(cart);
            cartDetail.setQuantity(cartItemDto.getQuantity());
            cartDetail.setDateAdded(new Date());
            cartDetailRepo.save(cartDetail);

        } else {
            String itemNotFound = messageSource.getMessage("ItemNotFound", null, Locale.ENGLISH);
            logger.error("ItemNotFound: {}", itemNotFound);
            throw new ItemNotFoundException();
        }
        if (customerOptional.isEmpty()) {
            String customerNotFound = messageSource.getMessage("CustomerNotFound", null, Locale.ENGLISH);
            logger.error("CustomerNotFound: {}", customerNotFound);
            throw new CustomerNotFoundException(cartItemDto.getCustomerId());
        }
        String cartAdded = messageSource.getMessage("CartAdded", null, Locale.ENGLISH);
        logger.info("CartAdded: {}", cartAdded);
        return showCartDto(cartItemDto.getCustomerId());
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
                    String customerNotFound = messageSource.getMessage("CustomerNotFound", null, Locale.ENGLISH);
                    logger.error("CustomerNotFound: {}", customerNotFound);
                    throw new CustomerNotFoundException(cartItemDto.getCustomerId());
                }
                String itemUpdated = messageSource.getMessage("ItemUpdated", null, Locale.ENGLISH);
                logger.info("ItemUpdated: {}", itemUpdated);
            } else {
                String itemNotFound = messageSource.getMessage("ItemNotFound", null, Locale.ENGLISH);
                logger.error("ItemNotFound: {}", itemNotFound);
                throw new ItemNotFoundException();
            }
        } else {
            String cartDetailNotFound = messageSource.getMessage("CartDetailNotFound", null, Locale.ENGLISH);
            logger.error("CartDetailNotFound: {}", cartDetailNotFound);
            throw new NotFoundCartDetailByIdException();
        }
    }

    @Override
    public void deleteItemFromCart(int cartDetailId) {
        Optional<CartDetail> CartDetailOptional = cartDetailRepo.findById(cartDetailId);
        if (CartDetailOptional.isEmpty()) {
            String cartDetailNotFound = messageSource.getMessage("CartDetailNotFound", null, Locale.ENGLISH);
            logger.error("CartDetailNotFound: {}", cartDetailNotFound);
            throw new NotFoundCartDetailByIdException();
        }
        String itemDeleted = messageSource.getMessage("ItemDeleted", null, Locale.ENGLISH);
        logger.info("ItemDeleted: {}", itemDeleted);
        cartDetailRepo.deleteById(cartDetailId);
    }

    @Override
    public List<CartDto> showAllCart() {
        List<Cart> list = cartRepo.findAll();
        return list.stream().map(CartMapper.INSTANCE::toDto).collect(Collectors.toList());
    }
}