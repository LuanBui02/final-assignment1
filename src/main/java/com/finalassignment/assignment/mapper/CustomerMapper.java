package com.finalassignment.assignment.mapper;

import com.finalassignment.assignment.dto.CartDto;
import com.finalassignment.assignment.dto.CustomerDto;
import com.finalassignment.assignment.dto.OrderDto;
import com.finalassignment.assignment.model.Cart;
import com.finalassignment.assignment.model.Customer;
import com.finalassignment.assignment.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "carts", target = "cartsDto")
    @Mapping(source = "orders", target = "ordersDto")
    CustomerDto toDto(Customer customer);

    List<CustomerDto> toListDto(List<Customer> customer);

    default Cart cartDtoToCart(CartDto cartDto) {
        return CartMapper.INSTANCE.toModel(cartDto);
    }

    default Order orderDtoToOrder(OrderDto orderDto) {
        return OrderMapper.INSTANCE.toModel(orderDto);
    }

    @Mapping(source = "cartsDto", target = "carts")
    @Mapping(source = "ordersDto", target = "orders")
    Customer toModel(CustomerDto customerDto);

    List<Customer> toListModel(List<CustomerDto> customerDto);

}
