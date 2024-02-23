package com.finalassignment.assignment.service.impl;

import com.finalassignment.assignment.dto.OrderCustomerDto;
import com.finalassignment.assignment.dto.OrderDto;
import com.finalassignment.assignment.exception.CustomerNotFoundException;
import com.finalassignment.assignment.exception.OrderNotFoundException;
import com.finalassignment.assignment.mapper.OrderMapper;
import com.finalassignment.assignment.model.Cart;
import com.finalassignment.assignment.model.CartDetail;
import com.finalassignment.assignment.model.Customer;
import com.finalassignment.assignment.model.Order;
import com.finalassignment.assignment.model.OrderDetail;
import com.finalassignment.assignment.repository.CartDetailRepo;
import com.finalassignment.assignment.repository.CartRepo;
import com.finalassignment.assignment.repository.CustomerRepo;
import com.finalassignment.assignment.repository.OrderDetailRepo;
import com.finalassignment.assignment.repository.OrderRepo;
import com.finalassignment.assignment.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderDetailRepo orderDetailRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private CartDetailRepo cartDetailRepo;
    @Autowired
    private MessageSource messageSource;
    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    private List<Order> showOrder(int customerId) {
        Optional<Customer> customerOptional = customerRepo.findById(customerId);

        if (customerOptional.isPresent()) {

            return orderRepo.findListOrderByCustomerId(customerId);
        }
        return null;
    }

    @Override
    public List<OrderDto> showOrderDto(int customerId) {
        Optional<Customer> customerOptional = customerRepo.findById(customerId);
        Optional<Order> orderOptional = orderRepo.findById(customerId);
        if (orderOptional.isEmpty()) {
            String customerNotFound = messageSource.getMessage("CustomerNotFound", null, Locale.ENGLISH);
            logger.error("CustomerNotFound: {}", customerNotFound);
            throw new OrderNotFoundException(customerId);
        } else {
            if (customerOptional.isEmpty()) {
                String customerNotFound = messageSource.getMessage("CustomerNotFound", null, Locale.ENGLISH);
                logger.error("CustomerNotFound: {}", customerNotFound);
                throw new CustomerNotFoundException(customerId);
            }
        }
        String orderFound = messageSource.getMessage("OrderFound", null, Locale.ENGLISH);
        logger.info("OrderFound: {}", orderFound);
        return OrderMapper.INSTANCE.toListDto(showOrder(customerId));
    }

    @Override
    public void addOrder(OrderCustomerDto orderCustomerDto) {
        Optional<Customer> customerOptional = customerRepo.findById(orderCustomerDto.getCustomerId());

        Optional<Cart> cartOptional = cartRepo.findCartByCustomerId(orderCustomerDto.getCustomerId());

        List<CartDetail> cartDetailOptional = cartDetailRepo.findListCartDetailByCartId(cartOptional.get().getId());

        Order order = null;

        if (customerOptional.isPresent()) {
            List<OrderDetail> orderDetailList = new ArrayList<>();
            order = new Order();
            order.setCustomer(customerOptional.get());
            order.setOrderDate(new Date());
            boolean checkTrue = true;
            order.setComplete(checkTrue);
            order.setOrderDetails(orderDetailList);
            order = orderRepo.save(order);
            for (CartDetail cartDetail : cartDetailOptional) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order);
                orderDetail.setItem(cartDetail.getItem());
                orderDetail.setQuantity(cartDetail.getQuantity());
                orderDetailRepo.save(orderDetail);
                cartDetailRepo.deleteById(cartDetail.getId());
            }
            order = orderRepo.findTopByCustomerIdOrderByOrderDateDesc(orderCustomerDto.getCustomerId());
        } else {
            String customerNotFound = messageSource.getMessage("CustomerNotFound", null, Locale.ENGLISH);
            logger.info("CustomerNotFound: {}", customerNotFound);
            throw new CustomerNotFoundException(orderCustomerDto.getCustomerId());
        }
        String orderAdded = messageSource.getMessage("OrderAdded", null, Locale.ENGLISH);
        logger.info("OrderAdded: {}", orderAdded);
        OrderMapper.INSTANCE.toDto(order);
    }

    @Override
    public OrderDto showOrderLatest(int customerId) {
        Optional<Customer> customerOptional = customerRepo.findById(customerId);
        Optional<Order> orderOptional = orderRepo.findById(customerId);
        if (orderOptional.isEmpty()) {
            String customerNotFound = messageSource.getMessage("CustomerNotFound", null, Locale.ENGLISH);
            logger.error("CustomerNotFound: {}", customerNotFound);
            throw new OrderNotFoundException(customerId);
        } else {
            if (customerOptional.isEmpty()) {
                String customerNotFound = messageSource.getMessage("CustomerNotFound", null, Locale.ENGLISH);
                logger.error("CustomerNotFound: {}", customerNotFound);
                throw new CustomerNotFoundException(customerId);
            }
        }
        String orderFound = messageSource.getMessage("OrderFound", null, Locale.ENGLISH);
        logger.info("OrderFound: {}", orderFound);
        return OrderMapper.INSTANCE.toDto(orderRepo.findTopByCustomerIdOrderByOrderDateDesc(customerId));
    }

    @Override
    public void deleteOrder(int orderId) {
        orderRepo.deleteById(orderId);
    }
}
