package com.doctor.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue
    private Long id;

    private String fistName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String address;

    private String createdAt;

    private String lastUpdate;

    private String note;


}
