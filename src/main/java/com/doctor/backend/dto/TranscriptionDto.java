package com.doctor.backend.dto;

import com.doctor.backend.model.Transcription;
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
public class TranscriptionDto {

    private Long id;

    private String note;

    private List<MedicationDto> medications;


    public static Transcription toEntity(TranscriptionDto transcriptionDto) {
        if (null == transcriptionDto)
            return null;
        return new ModelMapper().map(transcriptionDto, Transcription.class);
    }

    public static TranscriptionDto fromEntity(Transcription transcription) {
        if (transcription == null)
            return null;
        return new ModelMapper().map(transcription, TranscriptionDto.class);
    }

    public static List<TranscriptionDto> fromEntities(List<Transcription> notifications) {
        if (notifications == null)
            return new ArrayList<>();
        return notifications.stream().map(TranscriptionDto::fromEntity).collect(Collectors.toList());
    }

    public static List<Transcription> toEntities(List<TranscriptionDto> notifications) {
        if (notifications == null)
            return new ArrayList<>();
        return notifications.stream().map(TranscriptionDto::toEntity).collect(Collectors.toList());
    }
}