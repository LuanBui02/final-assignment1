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
@Table(name = "DetailCart")
public class DetailCart {
    @Id
    @GeneratedValue
    private int id;

    private int cartId;

    private int itemId;

    private int quantity;

    private int dateAdded;
}
