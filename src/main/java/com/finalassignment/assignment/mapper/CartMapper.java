package com.finalassignment.assignment.mapper;

import com.finalassignment.assignment.dto.CartDetailDto;
import com.finalassignment.assignment.dto.CartDto;
import com.finalassignment.assignment.model.Cart;
import com.finalassignment.assignment.model.CartDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    @Mapping(source = "cartDetails", target = "cartDetailsDto")
    @Mapping(source = "customer", target = "customerDto")
    CartDto toDto(Cart cart);

    default CartDetailDto cartDetailToCartDetailDto(CartDetail cartDetail) {
        return CartDetailMapper.INSTANCE.toDto(cartDetail);
    }

    @Mapping(source = "customerDto", target = "customer")
    @Mapping(source = "cartDetailsDto", target = "cartDetails")
    Cart toModel(CartDto cartDto);
}
