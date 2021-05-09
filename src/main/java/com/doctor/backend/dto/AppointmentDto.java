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

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentDto {

    private Long id;

    private String title;

    private String description;

    private PatientDto patient;

    public AppointmentDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public AppointmentDto(String title, String description, PatientDto patient) {

        this.title = title;
        this.description = description;
        this.patient = patient;
    }

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
