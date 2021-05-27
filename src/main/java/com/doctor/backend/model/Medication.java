package com.doctor.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "medication")
public class Medication extends AbstractEntity {

    private String name;

    private String route;

    private String drugForm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transcription_id")
    private Transcription transcription;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "dose_id")
    private Dose dose;

}
