package com.doctor.backend.utils;

import com.doctor.backend.model.user.User;
import com.doctor.backend.repository.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new User(1L, "", "Bilbo Baggins", "burglar")));
            log.info("Preloading " + repository.save(new User(2L, "", "Frodo Baggins", "thief")));
        };
    }
}
