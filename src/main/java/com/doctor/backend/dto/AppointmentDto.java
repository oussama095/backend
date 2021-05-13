package com.doctor.backend.dto;

import com.doctor.backend.model.Appointment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentDto {

    private Long id;

    @DateTimeFormat
    private LocalDateTime start;

    @DateTimeFormat
    private LocalDateTime end;

    private String title;

    private String description;

    public static Appointment toEntity(AppointmentDto appointmentDto) {
        if (null == appointmentDto)
            return null;
        return new ModelMapper().map(appointmentDto, Appointment.class);
    }

    public static AppointmentDto fromEntity(Appointment appointment) {
        if (appointment == null)
            return null;
        return new ModelMapper().map(appointment, AppointmentDto.class);

    }
}
