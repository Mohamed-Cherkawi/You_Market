package org.coders.youmarket.configuration;

import lombok.RequiredArgsConstructor;
import org.coders.youmarket.entities.AppUser;
import org.coders.youmarket.entities.Role;
import org.coders.youmarket.enums.AvailabilityStateEnum;
import org.coders.youmarket.enums.RoleEnum;
import org.coders.youmarket.repositories.RoleRepository;
import org.coders.youmarket.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Configuration @RequiredArgsConstructor
public class Data {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    @Transactional
    public CommandLineRunner insertSomeData() {

        return args -> {
            Role clientRole = new Role(RoleEnum.CLIENT);
            roleRepository.saveAll(List.of(new Role(RoleEnum.ADMIN), clientRole));

            userRepository.save(new AppUser(null , UUID.randomUUID().toString(),"Simox", passwordEncoder.encode("pass123"),"Mohamed","0618387383","EE03228", AvailabilityStateEnum.ONLINE ,clientRole));
        };
    }
}