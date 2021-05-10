package com.doctor.backend.utils;

import com.doctor.backend.dto.NotificationDto;
import com.doctor.backend.repository.user.NotificationRepository;
import com.doctor.backend.repository.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class LoadDatabase {
    //    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";

    @Bean
    CommandLineRunner initDatabase(UserRepository repository, NotificationRepository notificationRepository) {

        return args -> {
            var dateTime = LocalDateTime.now();
            var formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            notificationRepository.save(NotificationDto.toEntity(
                    new NotificationDto(1L, "Notification 1",
                            "This is a notification about something 1",
                            false, dateTime.format(formatter))));
            dateTime = LocalDateTime.now();
            formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            notificationRepository.save(NotificationDto.toEntity(
                    new NotificationDto(2L, "Notification 2",
                            "This is a notification about something 2",
                            false, dateTime.format(formatter))));
            dateTime = LocalDateTime.now();
            formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            notificationRepository.save(NotificationDto.toEntity(
                    new NotificationDto(3L, "Notification 3",
                            "This is a notification about something 3",
                            false, dateTime.format(formatter))));
        };
    }
}
