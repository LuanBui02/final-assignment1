package com.finalassignment.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "Carts")
public class Cart {
    @Id
    @GeneratedValue
    private int id;

    private int customerId;

    private int cartDetailId;
}
