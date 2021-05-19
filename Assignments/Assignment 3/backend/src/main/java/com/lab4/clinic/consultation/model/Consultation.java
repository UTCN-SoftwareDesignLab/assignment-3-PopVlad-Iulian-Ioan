package com.lab4.clinic.consultation.model;

import com.lab4.clinic.patient.model.Patient;
import com.lab4.clinic.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctorId", referencedColumnName = "id")
    private User doctorId;
    @ManyToOne
    @JoinColumn(name = "patientId", referencedColumnName = "id")
    private Patient patientId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date endDate;

}
