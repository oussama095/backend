package com.doctor.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient extends AbstractEntity {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    private String note;

    private String birthday;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;

}
