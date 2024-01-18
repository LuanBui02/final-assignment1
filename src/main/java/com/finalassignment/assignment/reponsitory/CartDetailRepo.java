package com.finalassignment.assignment.reponsitory;

import com.finalassignment.assignment.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailRepo extends JpaRepository<CartDetail, Integer> {
}
