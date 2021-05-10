package com.doctor.backend.dto;

import com.doctor.backend.model.Patient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

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


    public static Patient toEntity(PatientDto patientDto) {
        if (null == patientDto)
            return null;
        return new Patient(
                patientDto.getId(), patientDto.getFirstName(),
                patientDto.getLastName(), patientDto.getPhoneNumber(),
                patientDto.getEmail(), AddressDto.toEntity(patientDto.getAddress()),
                patientDto.getBirthday()
        );
    }

    public static PatientDto fromEntity(Patient patient) {
        if (patient == null)
            return null;
        return new PatientDto(
                patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getPhoneNumber(),
                patient.getEmail(),
                AddressDto.fromEntity(patient.getAddress()),
                patient.getBirthday()
        );
    }
}
