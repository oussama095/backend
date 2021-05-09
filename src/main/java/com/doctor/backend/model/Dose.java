package com.doctor.backend.model;

import com.doctor.backend.utils.Period;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@NoArgsConstructor
@Entity
@Table(name = "dose")
public class Dose {
    @Id
    @GeneratedValue
    private Long id;

    private Integer quantity;

    private Period period;


}
