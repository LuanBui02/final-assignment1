package com.finalassignment.assignment.reponsitory;

import com.finalassignment.assignment.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, Integer> {
}