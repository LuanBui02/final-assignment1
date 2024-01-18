package com.finalassignment.assignment.reponsitory;

import com.finalassignment.assignment.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepo extends JpaRepository<Items, Integer> {
}
