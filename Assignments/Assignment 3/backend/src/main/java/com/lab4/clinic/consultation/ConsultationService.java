package com.lab4.clinic.consultation;

import com.lab4.clinic.consultation.model.Consultation;
import com.lab4.clinic.consultation.model.dto.ConsultationDTO;
import com.lab4.clinic.patient.model.Patient;
import com.lab4.clinic.patient.model.dto.PatientDTO;
import com.lab4.clinic.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultationService {
    private final ConsultationMapper consultationMapper;
    private final ConsultationRepository consultationRepository;
    private Consultation findById(Long id) {
        return consultationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException ("Consultation not found: " + id));
    }
    public List<ConsultationDTO> findAll() {
        return consultationRepository.findAll().stream()
                .map( consultationMapper::toDto)
                .collect( Collectors.toList());
    }
    public ConsultationDTO create(ConsultationDTO consultation) {
        return consultationMapper.toDto( consultationRepository.save(
                consultationMapper.fromDto(consultation)
        ));
    }

    public ConsultationDTO edit(Long id, ConsultationDTO consultation) {
        Consultation actConsultation = findById(id);
        actConsultation.setDoctorId (consultation.getDoctorId ());
        actConsultation.setPatientId (consultation.getPatientId ());
        actConsultation.setStartDate ( consultation.getStartDate () );
        actConsultation.setEndDate ( consultation.getEndDate () );
        return consultationMapper.toDto(
                consultationRepository.save(actConsultation)
        );
    }
    public ConsultationDTO get(Long id) {
        return consultationMapper.toDto(findById(id));
    }
    public void delete(Long id) {
        consultationRepository.deleteById(id);
    }

    public List<Consultation>findAllByPatient(Patient patientId){
        return consultationRepository.findAllByPatientId ( patientId );
    }
}
