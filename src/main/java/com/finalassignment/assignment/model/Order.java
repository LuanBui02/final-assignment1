package com.finalassignment.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "is_complete")
    private boolean isComplete;
}
