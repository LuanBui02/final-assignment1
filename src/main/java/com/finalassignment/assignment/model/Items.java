package com.finalassignment.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Items")
public class Items {
    @Id
    @GeneratedValue
    private int id;

    private int orderDetailId;

    private String name;

    private double price;
}
