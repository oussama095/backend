package com.doctor.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    private String street1;

    private String street2;

    private String city;

    private Integer postalCode;

    private String country;

    public Address(String street1, String street2, String city, Integer postalCode, String country) {
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }
}
