package com.lab4.clinic.patient;


import com.lab4.clinic.patient.model.Patient;
import com.lab4.clinic.patient.model.dto.PatientDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientDTO toDto(Patient patient);

    Patient fromDto(PatientDTO patientDTO);
}
