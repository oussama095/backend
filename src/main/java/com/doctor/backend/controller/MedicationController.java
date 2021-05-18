package com.doctor.backend.controller;

import com.doctor.backend.dto.MedicationDto;
import com.doctor.backend.service.MedicationService;
import com.doctor.backend.service.TranscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medication")
public class MedicationController {

    private final TranscriptionService transcriptionService;
    private final MedicationService medicationService;

    @Autowired
    public MedicationController(TranscriptionService transcriptionService, MedicationService medicationService) {
        this.transcriptionService = transcriptionService;
        this.medicationService = medicationService;
    }

    @GetMapping("")
    public MedicationDto getMedication(@RequestParam Long medicationId) {
        return MedicationDto.fromEntity(medicationService.getMedicationById(medicationId));
    }

    @GetMapping("{transcriptionId}")
    public List<MedicationDto> getMedicationFromTransaction(@PathVariable Long transcriptionId) {
        return MedicationDto.fromEntities(transcriptionService.getMedicationByTranscription(transcriptionId));
    }

    @PostMapping("{transcriptionId}")
    public MedicationDto addMedication(@RequestBody MedicationDto medicationDto, @PathVariable Long transcriptionId) {
        return MedicationDto.fromEntity(transcriptionService.addMedicationToTranscription(transcriptionId, MedicationDto.toEntity(medicationDto)));
    }

    @PostMapping("")
    public MedicationDto updateMedication(@RequestBody MedicationDto medicationDto) {
        return MedicationDto.fromEntity(medicationService.updateMedication(MedicationDto.toEntity(medicationDto)));
    }

    @DeleteMapping("{medicationId}")
    public void removeMedication(@PathVariable Long medicationId) {
        medicationService.removeMedicationFromTranscription(medicationId);
    }


}
