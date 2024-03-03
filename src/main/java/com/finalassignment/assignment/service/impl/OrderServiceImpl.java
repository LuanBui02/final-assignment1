package com.finalassignment.assignment.service.impl;

import com.finalassignment.assignment.dto.CustomerDto;
import com.finalassignment.assignment.dto.OrderDto;
import com.finalassignment.assignment.exception.CustomerNotFoundException;
import com.finalassignment.assignment.exception.OrderNotFoundException;
import com.finalassignment.assignment.mapper.CustomerMapper;
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
import com.finalassignment.assignment.util.AbstractMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl extends AbstractMessage implements OrderService {
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
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private List<OrderDto> checkOrderEmpty(int customerId) {
        List<OrderDto> orderDto = OrderMapper.INSTANCE.toListDto(orderRepo.findListOrderByCustomerId(customerId));
        if (orderDto.isEmpty()) {
            logger.error("OrderNotFound: {}", getMessage("OrderNotFound"));
            throw new OrderNotFoundException(customerId);
        }
        return orderDto;
    }

    private CustomerDto checkCustomerEmpty(int customerId) {
        Optional<Customer> customerOptional = customerRepo.findById(customerId);
        if (customerOptional.isEmpty()) {
            logger.error("CustomerNotFound: {}", getMessage("CustomerNotFound"));
            throw new CustomerNotFoundException(customerId);
        }
        return CustomerMapper.INSTANCE.toDto(customerOptional.get());
    }

    private Order getOrderLatest(int customerId) {
        return orderRepo.findTopByCustomerIdOrderByOrderDateDesc(customerId);
    }

    @Override
    public List<OrderDto> showOrderDto(int customerId) {
        checkCustomerEmpty(customerId);
        List<OrderDto> orderDto = checkOrderEmpty(customerId);
        logger.info("OrderFound: {}", getMessage("OrderFound"));
        return orderDto;
    }

    @Override
    public void addOrder(OrderDto orderDto) {
        Optional<Cart> cartOptional = cartRepo.findCartByCustomerId(orderDto.getCustomerDto().getId());
        Order order = null;
        CustomerDto customerDto = checkCustomerEmpty(orderDto.getCustomerDto().getId());
        if (cartOptional.isPresent()) {
            List<CartDetail> cartDetailOptional = cartDetailRepo.findListCartDetailByCartId(cartOptional.get().getId());
            List<OrderDetail> orderDetailList = new ArrayList<>();
            order = Order.builder()
                    .customer(CustomerMapper.INSTANCE.toModel(customerDto))
                    .isComplete(true)
                    .orderDetails(orderDetailList)
                    .orderDate(new Date())
                    .build();
            order = orderRepo.save(order);
            for (CartDetail cartDetail : cartDetailOptional) {
                OrderDetail orderDetail = OrderDetail.builder()
                        .order(order)
                        .item(cartDetail.getItem())
                        .quantity(cartDetail.getQuantity())
                        .build();
                orderDetailRepo.save(orderDetail);
                cartDetailRepo.deleteById(cartDetail.getId());
            }
            order = getOrderLatest(orderDto.getCustomerDto().getId());
        }
        checkOrderEmpty(orderDto.getCustomerDto().getId());
        OrderMapper.INSTANCE.toDto(order);
        logger.info("OrderAdded: {}", getMessage("OrderAdded"));

    }

    @Override
    public OrderDto showOrderLatest(int customerId) {
        checkCustomerEmpty(customerId);
        checkOrderEmpty(customerId);
        OrderDto orderDto = OrderMapper.INSTANCE.toDto(getOrderLatest(customerId));
        logger.info("OrderFound: {}", getMessage("OrderFound"));
        return orderDto;
    }
}
