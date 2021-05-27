package com.doctor.backend.utils;

import com.doctor.backend.model.*;
import com.doctor.backend.repository.*;
import com.doctor.backend.security.model.ERole;
import com.doctor.backend.security.model.Role;
import com.doctor.backend.security.model.User;
import com.doctor.backend.security.repository.RoleRepository;
import com.doctor.backend.security.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Configuration
public class LoadDatabase {
    int maxNumber = 5;

    @Bean
    CommandLineRunner initDatabase(AppointmentRepository appointmentRepository,
                                   NotificationRepository notificationRepository,
                                   PatientRepository patientRepository,
                                   TranscriptionRepository transcriptionRepository,
                                   MedicationRepository medicationRepository,
                                   UserRepository userRepository,
                                   PasswordEncoder passwordEncoder,
                                   RoleRepository roleRepository) {
        return args -> {
            this.createRoles(roleRepository);
            this.createPatients(patientRepository);

            this.createUserIfNotFound(roleRepository, patientRepository, userRepository, passwordEncoder);

            this.createAppointment(appointmentRepository, patientRepository);
            this.initNotifications(notificationRepository, patientRepository);
            this.createTranscription(transcriptionRepository, patientRepository);
            this.createMedication(transcriptionRepository, medicationRepository);

        };
    }

    void createRoles(RoleRepository roleRepository) {
        var role = new Role();
        role.setName(ERole.ADMIN);
        role.setName(ERole.DOCTOR);
        roleRepository.save(role);
        role = new Role();
        role.setName(ERole.PATIENT);
        roleRepository.save(role);
    }

    void createAppointment(AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        var patients = new ArrayList<Patient>();
        patientRepository.findAll().forEach(patients::add);
        var date = LocalDateTime.now();
        for (var i = 0; i < this.maxNumber; i++) {
            for (var j = 0; j < this.maxNumber; j++) {
                var appointment = new Appointment();
                appointment.setTitle("Regular Appointment: " + j);
                appointment.setDescription("My organ " + j + " hurts");
                appointment.setStart(date.plusDays(j));
                appointment.setEnd(date.plusDays(j).plusHours(1));
                appointment.setPatient(patients.get(i));
                appointmentRepository.save(appointment);
            }
            date = date.plusHours(1);
        }

    }

    void createTranscription(TranscriptionRepository transcriptionRepository, PatientRepository patientRepository) {
        var patients = new ArrayList<Patient>();
        patientRepository.findAll().forEach(patients::add);
        for (var i = 0; i < this.maxNumber; i++) {
            for (var j = 0; j < this.maxNumber; j++) {
                var transcription = new Transcription();
                transcription.setName("Transcription Name " + j);
                transcription.setNote("note " + j);
                transcription.setPatient(patients.get(i));
                transcriptionRepository.save(transcription);
            }
        }
    }

    void createMedication(TranscriptionRepository transcriptionRepository, MedicationRepository medicationRepository) {
        var transcriptions = new ArrayList<Transcription>();
        transcriptionRepository.findAll().forEach(transcriptions::add);

        for (var i = 0; i < this.maxNumber; i++) {
            for (var j = 0; j < this.maxNumber; j++) {
                var dose = new Dose();
                dose.setQuantity(i + 1);
                dose.setFullPeriod(i + 2 + "months");
                dose.setPeriod("Day");
                var medication = new Medication();
                medication.setName("Medication " + j);
                medication.setRoute("Route " + j);
                medication.setDrugForm("Drug Form " + j);
                medication.setDose(dose);
                medication.setTranscription(transcriptions.get(i));
                medicationRepository.save(medication);
            }
        }
    }


    void createPatients(PatientRepository patientRepository) {
        var address = new Address("sechzhenerstr", "20", "Passau", 94032, "Germany");
        var patient = new Patient();
        patient.setFirstName("Oussama");
        patient.setLastName("Sakkat");
        patient.setPhoneNumber("+216 52 522 294");
        patient.setEmail("sakkat@mail.com");
        patient.setAddress(address);
        patient.setBirthday("1995-07-30T22:00:00.000Z");
        patientRepository.save(patient);

        for (var i = 1; i < this.maxNumber; i++) {
            patient = new Patient();
            address = new Address("sechzhenerstr", "20", "Passau", 94032, "Germany");
            address.setStreet2(String.valueOf(Integer.parseInt(address.getStreet2()) + i));
            patient.setFirstName("Patient " + i);
            patient.setLastName("LastName " + i);
            patient.setPhoneNumber("+216 52 522 294");
            patient.setEmail("patient" + i + "@mail.com");
            patient.setAddress(address);
            patient.setBirthday("199" + i + "-07-30T22:00:00.000Z");
            patientRepository.save(patient);
        }
    }

    void initNotifications(NotificationRepository repository, PatientRepository patientRepository) {
        var patients = new ArrayList<Patient>();
        patientRepository.findAll().forEach(patients::add);

        for (var i = 0; i < this.maxNumber; i++) {
            for (var j = 1; j < 4; j++) {
                var notification = new Notification();
                notification.setPatient(patients.get(i));
                notification.setTitle("Notification " + j);
                notification.setMessage("This is a notification about something " + j);
                notification.setIsRead(false);
                repository.save(notification);
            }
        }
    }

    void createUserIfNotFound(RoleRepository roleRepository, PatientRepository patientRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        var roles = roleRepository.findAll();
        var user = new User();
        user.setPassword(passwordEncoder.encode("root"));
        user.setEmail("root@root.com");
        user.setEnabled(true);
        user.setRoles(roles);
        userRepository.saveAndFlush(user);

        var patients = patientRepository.findAll();
        patients.forEach(patient -> {
            User patientUser = new User();
            patientUser.setPassword(passwordEncoder.encode(patient.getFirstName()));
            patientUser.setEmail(patient.getEmail());
            patientUser.setEnabled(true);
            var role = new ArrayList<Role>();
            var admin = roles.get(1);
            role.add(admin);
            patientUser.setRoles(role);
            patientUser.setPatient(patient);
            userRepository.saveAndFlush(patientUser);
        });

    }
}
