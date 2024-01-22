package com.finalassignment.assignment.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customers {
    @Id
    @GeneratedValue
    private int id;

    private String userName;

    private String password;

    private String type;
}

