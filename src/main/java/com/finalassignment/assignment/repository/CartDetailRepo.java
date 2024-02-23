package com.finalassignment.assignment.repository;

import com.finalassignment.assignment.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartDetailRepo extends JpaRepository<CartDetail, Integer> {
    List<CartDetail> findCartDetailsByCartId(int cartId);

    List<CartDetail> findListCartDetailByCartId(int cartId);

    Optional<CartDetail> findCartDetailByCartId(int cartId);
}

