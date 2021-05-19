package com.lab4.clinic.consultation;

import com.lab4.clinic.consultation.model.Consultation;
import com.lab4.clinic.consultation.model.dto.ConsultationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsultationMapper {
    ConsultationDTO toDto(Consultation consultation);

    Consultation fromDto(ConsultationDTO consultationDTO);
}
