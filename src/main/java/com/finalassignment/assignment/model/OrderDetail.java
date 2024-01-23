package com.finalassignment.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "item_id")
    private int itemId;
    @Column(name = "quantity")
    private int quantity;
}
