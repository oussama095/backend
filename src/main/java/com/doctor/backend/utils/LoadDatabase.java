package com.doctor.backend.utils;

import com.doctor.backend.dto.NotificationDto;
import com.doctor.backend.model.Address;
import com.doctor.backend.model.Appointment;
import com.doctor.backend.model.Patient;
import com.doctor.backend.repository.AppointmentRepository;
import com.doctor.backend.repository.NotificationRepository;
import com.doctor.backend.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);


    int maxNumber = 5;

    @Bean
    CommandLineRunner initDatabase(AppointmentRepository appointmentRepository,
                                   NotificationRepository notificationRepository,
                                   PatientRepository patientRepository) {
        return args -> {
            this.createPatients(patientRepository);
            this.createAppointment(appointmentRepository, patientRepository);

//            this.initNotifications(notificationRepository);
            //this.initAppointment();
        };
    }

    void createAppointment(AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        var patients = patientRepository.findAll();
        for (var i = 0; i < this.maxNumber; i++) {
            var appointment = new Appointment();
            appointment.setTitle("Title " + i);
            appointment.setDescription("description " + i);
            appointment.setPatient(patients.get(i));
            appointmentRepository.save(appointment);
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

    void initNotifications(NotificationRepository repository) {
        for (var i = 1; i < this.maxNumber; i++) {
            var notification = new NotificationDto(
                    "Notification " + i,
                    "This is a notification about something " + i);
            repository.save(NotificationDto.toEntity(notification));
        }
    }
}
