package com.finalassignment.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart_details")
public class CartDetail {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "cart_id")
    private int cartId;
    @Column(name = "item_id")
    private int itemId;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "date_added")
    private Date dateAdded;
}
