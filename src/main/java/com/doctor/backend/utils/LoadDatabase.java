package com.doctor.backend.utils;

import com.doctor.backend.dto.AddressDto;
import com.doctor.backend.dto.AppointmentDto;
import com.doctor.backend.dto.NotificationDto;
import com.doctor.backend.dto.PatientDto;
import com.doctor.backend.repository.user.AppointmentRepository;
import com.doctor.backend.repository.user.NotificationRepository;
import com.doctor.backend.repository.user.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadDatabase {
    //    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
    List<PatientDto> patients = new ArrayList<>();
    List<AppointmentDto> appointments = new ArrayList<>();
    int maxNumber = 5;

    @Bean
    CommandLineRunner initDatabase(AppointmentRepository appointmentRepository,
                                   NotificationRepository notificationRepository,
                                   PatientRepository patientRepository) {


        return args -> {
            this.initNotifications(notificationRepository);
            this.createPatients();
            this.createAppointment();

            this.initPatient(patientRepository);
            this.initAppointment(appointmentRepository);
        };
    }

    void createAppointment() {
        for (int i = 1; i < this.maxNumber; i++) {
            var appointment = new AppointmentDto("Title " + i, "description " + i).setPatient(this.patients.get(0));
            this.appointments.add(appointment);
        }
    }

    void createPatients() {
        var address = new AddressDto("sechzhenerstr", "20", "Passau", 94032, "Germany");
        var patient = new PatientDto("Oussama", "sakkat", "+216 52 522 294",
                "sakkat@mail.com", address, "1995-07-30T22:00:00.000Z");
        this.patients.add(patient);
        for (int i = 1; i < this.maxNumber; i++) {
            address.setStreet2(String.valueOf(Integer.parseInt(address.getStreet2()) + i));
            patient = new PatientDto("Patient " + i, "LastName " + i, "+216 52 522 294",
                    "patient" + i + "@mail.com", address, "199" + i + "-07-30T22:00:00.000Z");
            this.patients.add(patient);
        }
    }

    void initPatient(PatientRepository repository) {
        this.patients.forEach(patientDto -> repository.save(PatientDto.toEntity(patientDto)));
    }

    void initAppointment(AppointmentRepository repository) {
        this.appointments.forEach(appointmentDto -> repository.save(AppointmentDto.toEntity(appointmentDto)));
    }

    void initNotifications(NotificationRepository repository) {
        for (int i = 1; i < this.maxNumber; i++) {
            var notification = new NotificationDto(
                    "Notification " + i,
                    "This is a notification about something " + i);
            repository.save(NotificationDto.toEntity(notification));
        }
    }
}
