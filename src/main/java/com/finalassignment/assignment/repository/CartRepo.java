package com.finalassignment.assignment.repository;

import com.finalassignment.assignment.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {
    public Optional<Cart> findCartByCustomerId(int customerId);
}
