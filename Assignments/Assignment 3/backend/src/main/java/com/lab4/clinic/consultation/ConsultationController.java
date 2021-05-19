package com.lab4.clinic.consultation;


import com.lab4.clinic.consultation.model.dto.ConsultationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.clinic.UrlMapping.CONSULTATIONS;
import static com.lab4.clinic.UrlMapping.ENTITY;

@RestController
@RequestMapping(CONSULTATIONS)
@RequiredArgsConstructor
public class ConsultationController {
    private final ConsultationService consultationService;

    @GetMapping
    public List<ConsultationDTO> allConsultations() {
        return consultationService.findAll();
    }

    @PostMapping
    public ConsultationDTO create(@RequestBody ConsultationDTO consultation) {
        return consultationService.create(consultation);
    }

    @PatchMapping(ENTITY)
    public ConsultationDTO edit(@PathVariable Long id, @RequestBody ConsultationDTO consultation) {
        return consultationService.edit(id,consultation);
    }
    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        consultationService.delete(id);
    }

    @GetMapping(ENTITY)
    public ConsultationDTO getConsultation(@PathVariable Long id) {
        return consultationService.get(id);
    }

}
