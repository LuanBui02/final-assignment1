package com.finalassignment.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart_details")
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "date_added")
    private Date dateAdded;


}
