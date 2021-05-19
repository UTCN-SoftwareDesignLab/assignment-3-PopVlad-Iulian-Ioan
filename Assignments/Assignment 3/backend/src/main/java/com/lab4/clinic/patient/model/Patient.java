package com.lab4.clinic.patient.model;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String icn;
    @Column
    private String cnp;
    @Column
    private Date dateOfBirth;
    @Column
    private String address;
}
