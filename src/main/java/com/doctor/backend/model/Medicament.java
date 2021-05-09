package com.doctor.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "medicament")
public class Medicament {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transcription_id")
    private Transcription transcriptionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dose_id")
    private Dose doseId;
}
