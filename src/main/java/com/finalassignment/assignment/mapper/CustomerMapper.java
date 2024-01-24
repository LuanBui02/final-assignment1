package com.finalassignment.assignment.mapper;

import com.finalassignment.assignment.dto.CustomerDto;
import com.finalassignment.assignment.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    @Mapping(source = "carts", target = "cartsDto")
    @Mapping(source = "orders", target = "ordersDto")
    CustomerDto ModelToDto(Customer customer);
    @Mapping(source = "cartsDto", target = "carts")
    @Mapping(source = "ordersDto", target = "orders")
    Customer DtoToModel(CustomerDto  customerDto);
}
