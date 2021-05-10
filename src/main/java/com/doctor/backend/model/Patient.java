package com.doctor.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    private String createdAt;

    private String lastUpdate;

    private String note;

    private String birthday;

    public Patient(Long id, String firstName, String lastName, String phoneNumber, String email, Address address, String birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
    }
}
