package com.lab4.clinic.patient;


import com.lab4.clinic.patient.model.Patient;
import com.lab4.clinic.patient.model.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    private Patient findById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException ("Patient not found: " + id));
    }
    public List<PatientDTO> findAll() {
        return patientRepository.findAll().stream()
                .map( patientMapper::toDto)
                .collect( Collectors.toList());
    }

    public PatientDTO create(PatientDTO patient) {
        return patientMapper.toDto( patientRepository.save(
                patientMapper.fromDto(patient)
        ));
    }

    public PatientDTO edit(Long id, PatientDTO patient) {
        Patient actPatient = findById(id);
        actPatient.setName (patient.getName ());
        actPatient.setIcn (patient.getIcn ());
        actPatient.setCnp ( patient.getCnp () );
        actPatient.setDateOfBirth ( patient.getDateOfBirth () );
        actPatient.setAddress ( patient.getAddress () );
        return patientMapper.toDto(
                patientRepository.save(actPatient)
        );
    }
    public PatientDTO get(Long id) {
        return patientMapper.toDto(findById(id));
    }

}
