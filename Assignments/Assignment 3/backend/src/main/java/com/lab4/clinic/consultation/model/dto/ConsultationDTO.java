package com.lab4.clinic.consultation.model.dto;

import com.lab4.clinic.patient.model.Patient;
import com.lab4.clinic.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationDTO {
    private Long id;
    private User doctorId;
    private Patient patientId;
    private Date startDate;
    private Date endDate;
}
