package com.doctor.backend.controller;

import com.doctor.backend.dto.TranscriptionDto;
import com.doctor.backend.service.PatientService;
import com.doctor.backend.service.TranscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transcription")
public class TranscriptionController {
    private final TranscriptionService transcriptionService;
    private final PatientService patientService;

    @Autowired
    public TranscriptionController(TranscriptionService transcriptionService, PatientService patientService) {
        this.transcriptionService = transcriptionService;
        this.patientService = patientService;
    }

    @GetMapping("{patientId}")
    public List<TranscriptionDto> all(@PathVariable Long patientId) {
        return TranscriptionDto.fromEntities(patientService.getTranscriptionByPatient(patientId));
    }

    @PostMapping("{patientId}")
    public TranscriptionDto addTranscription(@PathVariable Long patientId, @RequestBody TranscriptionDto transcriptionDto) {
        return TranscriptionDto.fromEntity(patientService.addTranscription(patientId, TranscriptionDto.toEntity(transcriptionDto)));
    }

    @PutMapping("")
    public TranscriptionDto updateTranscription(@RequestBody TranscriptionDto transcriptionDto) {
        return TranscriptionDto.fromEntity(transcriptionService.updateTranscription(TranscriptionDto.toEntity(transcriptionDto)));
    }


    @DeleteMapping("{transcriptionId}")
    public void deleteTranscription(@PathVariable Long transcriptionId) {
        transcriptionService.deleteTranscription(transcriptionId);
    }

}
