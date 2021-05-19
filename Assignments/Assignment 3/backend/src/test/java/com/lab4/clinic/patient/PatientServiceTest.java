package com.lab4.clinic.patient;

import com.lab4.clinic.TestCreationFactory;
import com.lab4.clinic.patient.model.Patient;
import com.lab4.clinic.patient.model.dto.PatientDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.lab4.clinic.TestCreationFactory.randomLong;
import static com.lab4.clinic.TestCreationFactory.randomString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private PatientMapper patientMapper;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        patientService = new PatientService(patientRepository, patientMapper);
    }
    @Test
    void findAll() {
        List<Patient> patients = TestCreationFactory.listOf(Patient.class);
        when(patientRepository.findAll()).thenReturn(patients);

        List<PatientDTO> all = patientService.findAll();
        Assertions.assertEquals(all.size(),patients.size());
    }

    @Test
    void create() {
        Long id=randomLong ();
        String address=randomString ();
        String name=randomString ();
        String cnp=randomString ();
        Date dateOfBirth= new Date ();
        String icn = randomString ();
        PatientDTO patientDTO = PatientDTO.builder ()
                .id ( id )
                .address ( address )
                .name ( name )
                .cnp ( cnp )
                .dateOfBirth ( dateOfBirth )
                .icn ( icn )
                .build ();
        Patient patient = Patient.builder()
                .id ( id )
                .address ( address )
                .name ( name )
                .cnp ( cnp )
                .dateOfBirth ( dateOfBirth )
                .icn ( icn )
                .build ();
        when(patientMapper.toDto(patient)).thenReturn(patientDTO);
        when(patientMapper.fromDto(patientDTO)).thenReturn(patient);
        when(patientRepository.save(patient)).thenReturn(patient);
        PatientDTO newPatient = patientService.create(patientDTO);
        Assertions.assertNotNull(newPatient);
    }

    @Test
    void edit() {
        Long id=randomLong ();
        String address=randomString ();
        String name=randomString ();
        String cnp=randomString ();
        Date dateOfBirth= new Date ();
        String icn = randomString ();
        PatientDTO patientDTO = PatientDTO.builder ()
                .id ( id )
                .address ( address )
                .name ( name )
                .cnp ( cnp )
                .dateOfBirth ( dateOfBirth )
                .icn ( icn )
                .build ();
        Patient patient = Patient.builder()
                .id ( id )
                .address ( address )
                .name ( name )
                .cnp ( cnp )
                .dateOfBirth ( dateOfBirth )
                .icn ( icn )
                .build ();
        when(patientMapper.toDto(patient)).thenReturn(patientDTO);
        when(patientMapper.fromDto(patientDTO)).thenReturn(patient);
        when(patientRepository.findById(patient.getId())).thenReturn( Optional.of(patient));
        when(patientRepository.save(patient)).thenReturn(patient);
        PatientDTO updatedPatient = patientService.edit(id,patientDTO);
        Assertions.assertEquals(patientDTO.getId(), patientService.edit(id,patientDTO).getId());
        Assertions.assertNotNull(updatedPatient);
    }

    @Test
    void get() {
        Long id=randomLong ();
        String address=randomString ();
        String name=randomString ();
        String cnp=randomString ();
        Date dateOfBirth= new Date ();
        String icn = randomString ();
        PatientDTO patientDTO = PatientDTO.builder ()
                .id ( id )
                .address ( address )
                .name ( name )
                .cnp ( cnp )
                .dateOfBirth ( dateOfBirth )
                .icn ( icn )
                .build ();
        Patient patient = Patient.builder()
                .id ( id )
                .address ( address )
                .name ( name )
                .cnp ( cnp )
                .dateOfBirth ( dateOfBirth )
                .icn ( icn )
                .build ();
        when(patientMapper.toDto(patient)).thenReturn(patientDTO);
        when(patientMapper.fromDto(patientDTO)).thenReturn(patient);
        when(patientRepository.findById(patient.getId())).thenReturn( Optional.of(patient));
        when(patientRepository.save(patient)).thenReturn(patient);
        Assertions.assertEquals(patientDTO.getId(), patientService.get(id).getId());
    }
}