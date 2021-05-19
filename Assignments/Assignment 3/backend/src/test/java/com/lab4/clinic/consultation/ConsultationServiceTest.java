package com.lab4.clinic.consultation;

import com.lab4.clinic.BaseControllerTest;
import com.lab4.clinic.TestCreationFactory;
import com.lab4.clinic.consultation.model.Consultation;
import com.lab4.clinic.consultation.model.dto.ConsultationDTO;
import com.lab4.clinic.patient.model.dto.PatientDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.lab4.clinic.TestCreationFactory.randomLong;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ConsultationServiceTest {
    @InjectMocks
    private ConsultationService consultationService;

    @Mock
    private ConsultationRepository consultationRepository;

    @Mock
    private ConsultationMapper consultationMapper;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        consultationService = new ConsultationService ( consultationMapper,consultationRepository);
    }
    @Test
    void findAll() {
        List<Consultation> consultations = TestCreationFactory.listOf(Consultation.class);
        when(consultationRepository.findAll()).thenReturn(consultations);

        List<ConsultationDTO> all = consultationService.findAll();
        Assertions.assertEquals(all.size(),consultations.size());
    }
    @Test
    void create() {
        Long id = randomLong();
        Date startDate= new Timestamp(System.currentTimeMillis());
        Date endDate=new Timestamp(System.currentTimeMillis());
        ConsultationDTO consultationDTO=ConsultationDTO.builder()
                .id(id)
                .startDate ( startDate )
                .endDate ( endDate)
                .build();
        Consultation consultation=Consultation.builder()
                .id(id)
                .startDate ( startDate )
                .endDate ( endDate)
                .build();
        when(consultationMapper.toDto(consultation)).thenReturn(consultationDTO);
        when(consultationMapper.fromDto(consultationDTO)).thenReturn(consultation);
        when(consultationRepository.save(consultation)).thenReturn(consultation);
        ConsultationDTO newPatient = consultationService.create(consultationDTO);
        Assertions.assertNotNull(newPatient);
    }

    @Test
    void edit() {
        Long id = randomLong();
        Date startDate= new Timestamp(System.currentTimeMillis());
        Date endDate=new Timestamp(System.currentTimeMillis());
        ConsultationDTO consultationDTO=ConsultationDTO.builder()
                .id(id)
                .startDate ( startDate )
                .endDate ( endDate)
                .build();
        Consultation consultation=Consultation.builder()
                .id(id)
                .startDate ( startDate )
                .endDate ( endDate)
                .build();
        when(consultationMapper.toDto(consultation)).thenReturn(consultationDTO);
        when(consultationMapper.fromDto(consultationDTO)).thenReturn(consultation);
        when(consultationRepository.findById(consultation.getId())).thenReturn( Optional.of(consultation));
        when(consultationRepository.save(consultation)).thenReturn(consultation);

        ConsultationDTO updatedConsultation = consultationService.edit(id,consultationDTO);
        Assertions.assertEquals(consultationDTO.getId(), consultationService.edit(id,consultationDTO).getId());
        Assertions.assertNotNull(updatedConsultation);
    }

    @Test
    void get() {
        Long id = randomLong();
        Date startDate= new Timestamp(System.currentTimeMillis());
        Date endDate=new Timestamp(System.currentTimeMillis());
        ConsultationDTO consultationDTO=ConsultationDTO.builder()
                .id(id)
                .startDate ( startDate )
                .endDate ( endDate)
                .build();
        Consultation consultation=Consultation.builder()
                .id(id)
                .startDate ( startDate )
                .endDate ( endDate)
                .build();
        when(consultationMapper.toDto(consultation)).thenReturn(consultationDTO);
        when(consultationMapper.fromDto(consultationDTO)).thenReturn(consultation);
        when(consultationRepository.findById(consultation.getId())).thenReturn( Optional.of(consultation));
        when(consultationRepository.save(consultation)).thenReturn(consultation);
        Assertions.assertEquals(consultationDTO.getId(), consultationService.get(id).getId());

    }

    @Test
    void delete() {
        Long id = randomLong();
        Date startDate= new Timestamp(System.currentTimeMillis());
        Date endDate=new Timestamp(System.currentTimeMillis());
        ConsultationDTO consultationDTO=ConsultationDTO.builder()
                .id(id)
                .startDate ( startDate )
                .endDate ( endDate)
                .build();
        Consultation consultation=Consultation.builder()
                .id(id)
                .startDate ( startDate )
                .endDate ( endDate)
                .build();
        when(consultationRepository.findById(consultation.getId())).thenReturn(Optional.of(consultation));
        consultationService.delete(consultationDTO.getId());
    }
}