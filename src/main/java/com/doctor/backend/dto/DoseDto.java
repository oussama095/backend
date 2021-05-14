package com.doctor.backend.dto;

import com.doctor.backend.model.Dose;
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
public class DoseDto {
    
    private Long id;

    private Integer quantity;

    private String period;

    private String fullPeriod;

    public static Dose toEntity(DoseDto doseDto) {
        if (null == doseDto)
            return null;
        return new ModelMapper().map(doseDto, Dose.class);
    }

    public static DoseDto fromEntity(Dose dose) {
        if (dose == null)
            return null;
        return new ModelMapper().map(dose, DoseDto.class);
    }

    public static List<DoseDto> fromEntities(List<Dose> notifications) {
        if (notifications == null)
            return new ArrayList<>();
        return notifications.stream().map(DoseDto::fromEntity).collect(Collectors.toList());
    }

    public static List<Dose> toEntities(List<DoseDto> notifications) {
        if (notifications == null)
            return new ArrayList<>();
        return notifications.stream().map(DoseDto::toEntity).collect(Collectors.toList());
    }
}
