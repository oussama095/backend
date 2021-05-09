package com.doctor.backend.dto;

import com.doctor.backend.model.Patient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private AddressDto address;

    private String birthday;

    private List<AppointmentDto> appointments;

    public PatientDto(String firstName, String lastName, String phoneNumber, String email, AddressDto address, String birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
    }

    public static Patient toEntity(PatientDto patientDto) {
        if (null == patientDto)
            return null;
        return new ModelMapper().map(patientDto, Patient.class);
    }

    public static PatientDto fromEntity(Patient patient) {
        if (patient == null)
            return null;
        return new ModelMapper().map(patient, PatientDto.class);

    }
}
