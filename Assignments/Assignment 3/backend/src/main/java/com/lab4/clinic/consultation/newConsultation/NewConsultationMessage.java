package com.lab4.clinic.consultation.newConsultation;

import com.lab4.clinic.consultation.model.Consultation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewConsultationMessage {
    private Consultation newConsultation;
    private String message;

    @Override
    public String toString() {
        return "You have a new consultation with "+ newConsultation.getPatientId ()+
                " from "+newConsultation.getStartDate ()+" to "+newConsultation.getEndDate ();
    }
}
