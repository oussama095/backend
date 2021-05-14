package com.doctor.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Dose extends AbstractEntity {

    private Integer quantity;

    private String period;

    private String fullPeriod;

}
