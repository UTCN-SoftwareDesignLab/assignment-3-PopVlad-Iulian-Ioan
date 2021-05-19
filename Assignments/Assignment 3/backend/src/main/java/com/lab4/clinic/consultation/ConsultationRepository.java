package com.lab4.clinic.consultation;

import com.lab4.clinic.consultation.model.Consultation;
import com.lab4.clinic.patient.model.Patient;
import com.lab4.clinic.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long>, JpaSpecificationExecutor<Consultation> {
    List<Consultation> findAllByPatientId(Patient patientId);
}
