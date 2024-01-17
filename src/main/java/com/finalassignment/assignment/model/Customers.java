package com.finalassignment.assignment.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Customer")
public class Customers {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String address;
    private double amountBalance;
}
