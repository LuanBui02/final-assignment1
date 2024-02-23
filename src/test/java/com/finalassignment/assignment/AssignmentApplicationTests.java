package com.finalassignment.assignment;

import com.finalassignment.assignment.dto.CartDto;
import com.finalassignment.assignment.mapper.CartMapper;
import com.finalassignment.assignment.mapper.CustomerMapper;
import com.finalassignment.assignment.model.Cart;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AssignmentApplicationTests {
    private final CartMapper cartMapper = Mappers.getMapper(CartMapper.class);
    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    @Test
    void ModelToDto() {
        CartDto cart = new CartDto();
        cart.setId(1);

        Cart model = cartMapper.toModel(cart);
        assertEquals(cart.getId(), model.getId());
    }

}
