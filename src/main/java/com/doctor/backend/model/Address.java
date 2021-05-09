package com.doctor.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Address")
public class Address extends AbstractEntity {

    private String street1;

    private String street2;

    private String city;

    private Integer postalCode;

    private String country;

}
