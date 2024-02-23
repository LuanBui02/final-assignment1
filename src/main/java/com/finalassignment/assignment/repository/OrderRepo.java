package com.finalassignment.assignment.repository;

import com.finalassignment.assignment.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    public List<Order> findListOrderByCustomerId(int customerId);

    Order findTopByCustomerIdOrderByOrderDateDesc(int customerId);
}
