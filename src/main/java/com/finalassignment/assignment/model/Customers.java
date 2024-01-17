package com.finalassignment.assignment.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Customers")
public class Customers {
    @Id
    @GeneratedValue
    private int id;

    private int userId;

    private String userName;

    private String password;
}
