package com.doctor.backend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address extends AbstractEntity {

    private String street1;

    private String street2;

    private String city;

    private Integer postalCode;

    private String country;

}
