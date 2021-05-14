package com.doctor.backend.dto;

import com.doctor.backend.model.Medication;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MedicationDto {

    private Long id;

    private String name;

    private String route;

    private String drugForm;

    private DoseDto dose;


    public static Medication toEntity(MedicationDto medicationDto) {
        if (null == medicationDto)
            return null;
        return new ModelMapper().map(medicationDto, Medication.class);
    }

    public static MedicationDto fromEntity(Medication medication) {
        if (medication == null)
            return null;
        return new ModelMapper().map(medication, MedicationDto.class);
    }

    public static List<MedicationDto> fromEntities(List<Medication> notifications) {
        if (notifications == null)
            return new ArrayList<>();
        return notifications.stream().map(MedicationDto::fromEntity).collect(Collectors.toList());
    }

    public static List<Medication> toEntities(List<MedicationDto> notifications) {
        if (notifications == null)
            return new ArrayList<>();
        return notifications.stream().map(MedicationDto::toEntity).collect(Collectors.toList());
    }
}
