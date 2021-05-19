package com.lab4.clinic.consultation.newConsultation;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NewConsultationController {

    @MessageMapping("message")
    @SendTo("/newConsult/message")
    public String message(NewConsultationMessage consultationMessage){
        return consultationMessage.toString ();
    }

}
