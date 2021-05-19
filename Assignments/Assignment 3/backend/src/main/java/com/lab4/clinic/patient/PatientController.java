package com.lab4.clinic.patient;

import com.lab4.clinic.patient.model.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.clinic.UrlMapping.ENTITY;
import static com.lab4.clinic.UrlMapping.PATIENTS;

@RestController
@RequestMapping(PATIENTS)
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public List<PatientDTO> allPatients() {
        return patientService.findAll();
    }

    @PostMapping
    public PatientDTO create(@RequestBody PatientDTO patient) {
        return patientService.create(patient);
    }

    @PatchMapping(ENTITY)
    public PatientDTO edit(@PathVariable Long id, @RequestBody PatientDTO patient) {
        return patientService.edit(id,patient);
    }
    @GetMapping(ENTITY)
    public PatientDTO getPatient(@PathVariable Long id) {
        return patientService.get(id);
    }

}
