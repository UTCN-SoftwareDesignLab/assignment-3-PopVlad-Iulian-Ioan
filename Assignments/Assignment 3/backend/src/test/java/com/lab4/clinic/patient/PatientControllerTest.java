package com.lab4.clinic.patient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lab4.clinic.patient.model.Patient;
import com.lab4.clinic.patient.model.dto.PatientDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.lab4.clinic.BaseControllerTest;
import com.lab4.clinic.TestCreationFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.lab4.clinic.TestCreationFactory.randomLong;
import static com.lab4.clinic.TestCreationFactory.randomString;
import static com.lab4.clinic.UrlMapping.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PatientControllerTest extends BaseControllerTest{
    @InjectMocks
    private PatientController controller;

    @Mock
    private PatientService patientService;

    @BeforeEach
    protected void setUp(){
        super.setUp ();
        controller=new PatientController ( patientService );
        mockMvc=MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver ())
                .build();
    }
    @Test
    void allPatients() throws Exception {
        List<PatientDTO> patient = TestCreationFactory.listOf( Patient.class);
        when(patientService.findAll()).thenReturn(patient);

        ResultActions response = mockMvc.perform(get(PATIENTS));

        response.andExpect(status().isOk())
                .andExpect(jsonContentToBe(patient));
    }

    @Test
    void create() throws Exception {
        PatientDTO reqPatient = TestCreationFactory.newPatientDTO ();

        when(patientService.create(reqPatient)).thenReturn(reqPatient);

        ResultActions result = performPostWithRequestBody(PATIENTS, reqPatient);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(reqPatient));
    }

    @Test
    void edit() throws Exception {
        long id = randomLong ();
        PatientDTO reqPatient = PatientDTO.builder ()
                .id ( id )
                .address ( randomString () )
                .name ( randomString () )
                .cnp ( randomString () )
                .dateOfBirth ( new Date () )
                .icn ( randomString () )
                .build ();

        when ( patientService.edit ( id , reqPatient ) ).thenReturn ( reqPatient );

        ResultActions result = performPatchWithRequestBodyAndPathVariable ( PATIENTS + ENTITY , reqPatient , id );
        result.andExpect ( status ().isOk () )
                .andExpect ( jsonContentToBe ( reqPatient ) );
    }
    @Test
    void getPatient() throws Exception {
        long id = randomLong();
        PatientDTO reqPatient = PatientDTO.builder ()
                .id ( id )
                .address ( randomString () )
                .name ( randomString () )
                .cnp ( randomString () )
                .dateOfBirth ( new Date () )
                .icn ( randomString () )
                .build ();
        when(patientService.get(id)).thenReturn(reqPatient);

        ResultActions result = performGetWithPathVariable(PATIENTS + ENTITY, id);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(reqPatient));
    }
}