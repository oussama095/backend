package com.doctor.backend.controller;

import com.doctor.backend.dto.MedicationDto;
import com.doctor.backend.service.MedicationService;
import com.doctor.backend.service.PatientService;
import com.doctor.backend.service.TranscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medication")
public class MedicationController {

    private final TranscriptionService transcriptionService;
    private final MedicationService medicationService;
    private final PatientService patientService;

    @Autowired
    public MedicationController(TranscriptionService transcriptionService, MedicationService medicationService, PatientService patientService) {
        this.transcriptionService = transcriptionService;
        this.medicationService = medicationService;
        this.patientService = patientService;
    }

    @GetMapping("")
    public MedicationDto getMedication(@RequestParam Long medicationId) {

        return MedicationDto.fromEntity(medicationService.getMedicationById(medicationId));
    }

    @GetMapping("all")
    public List<MedicationDto> getMedicationFromTransaction(@RequestParam(required = false) Optional<Long> transcriptionId,
                                                            @RequestParam(required = false) Optional<Long> patientId) {

        if (transcriptionId.isPresent()) {
            return MedicationDto.fromEntities(transcriptionService.getMedicationByTranscription(transcriptionId.get()));
        }
        if (patientId.isPresent()) {
            return MedicationDto.fromEntities(patientService.getMedicationByPatient(patientId.get()));
        }
        return new ArrayList<>();
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
