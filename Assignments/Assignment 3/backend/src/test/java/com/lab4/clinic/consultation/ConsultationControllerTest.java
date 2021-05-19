package com.lab4.clinic.consultation;

import com.lab4.clinic.BaseControllerTest;
import com.lab4.clinic.TestCreationFactory;
import com.lab4.clinic.consultation.model.Consultation;
import com.lab4.clinic.consultation.model.dto.ConsultationDTO;
import com.lab4.clinic.patient.PatientController;
import com.lab4.clinic.patient.PatientService;
import com.lab4.clinic.patient.model.Patient;
import com.lab4.clinic.patient.model.dto.PatientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static com.lab4.clinic.TestCreationFactory.*;
import static com.lab4.clinic.UrlMapping.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ConsultationControllerTest extends BaseControllerTest {
    @InjectMocks
    private ConsultationController controller;

    @Mock
    private ConsultationService consultationService;

    @BeforeEach
    protected void setUp(){
        super.setUp ();
        controller=new ConsultationController ( consultationService );
        mockMvc= MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver ())
                .build();
    }
    @Test
    void allConsultations() throws Exception {
        List<ConsultationDTO> consultation= TestCreationFactory.listOf( Consultation.class);
        when(consultationService.findAll()).thenReturn(consultation);

        ResultActions response = mockMvc.perform(get(CONSULTATIONS));

        response.andExpect(status().isOk())
                .andExpect(jsonContentToBe(consultation));
    }

    @Test
    void delete() throws Exception {
        long id = randomLong();
        doNothing().when(consultationService).delete(id);

        ResultActions result = performDeleteWIthPathVariable(CONSULTATIONS + ENTITY, id);
        verify(consultationService, times(1)).delete(id);

        result.andExpect(status().isOk());
    }

}