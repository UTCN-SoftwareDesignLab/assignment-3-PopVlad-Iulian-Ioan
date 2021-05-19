package com.lab4.clinic;

import com.lab4.clinic.consultation.ConsultationRepository;
import com.lab4.clinic.consultation.ConsultationService;
import com.lab4.clinic.consultation.model.Consultation;
import com.lab4.clinic.patient.PatientRepository;
import com.lab4.clinic.patient.PatientService;
import com.lab4.clinic.security.AuthService;
import com.lab4.clinic.security.dto.SignupRequest;
import com.lab4.clinic.user.RoleRepository;
import com.lab4.clinic.user.UserRepository;
import com.lab4.clinic.user.model.ERole;
import com.lab4.clinic.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final PatientRepository patientRepository;

    private final ConsultationRepository consultationRepository;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {

            userRepository.deleteAll();
            roleRepository.deleteAll();
            patientRepository.deleteAll ();
            consultationRepository.deleteAll ();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("admin@email.com")
                    .username("admin")
                    .password("WooHoo1!")
                    .roles(Set.of("ADMIN"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("doctor@email.com")
                    .username("doctor")
                    .password("WooHoo1!")
                    .roles(Set.of("DOCTOR"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("secretary@email.com")
                    .username("secretary")
                    .password("WooHoo1!")
                    .roles(Set.of("SECRETARY"))
                    .build());
        }
    }
}
