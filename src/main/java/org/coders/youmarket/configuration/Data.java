package org.coders.youmarket.configuration;

import lombok.RequiredArgsConstructor;
import org.coders.youmarket.entities.AppUser;
import org.coders.youmarket.enums.AvailabilityStateEnum;
import org.coders.youmarket.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;


@Configuration @RequiredArgsConstructor
public class Data {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    @Transactional
    public CommandLineRunner insertSomeData() {
        AppUser user = AppUser.builder().
                reference(UUID.randomUUID().toString()).
                username("Simox").
                password(passwordEncoder.encode("pass123")).
                name("Mohamed").
                status(AvailabilityStateEnum.ONLINE).
                phone("0618387383").
                createdAt(LocalDateTime.now()).build();

        return args -> userRepository.save(user);
    }
}