package com.spring.boot.resturantbackend.config;

import com.spring.boot.resturantbackend.models.security.Role;
import com.spring.boot.resturantbackend.repositories.security.RoleRepo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RoleDataInitializer {
    @Bean
    ApplicationRunner initializeRoles(RoleRepo roleRepo) {
        return args -> List.of("USER", "ADMIN").forEach(roleName ->
                roleRepo.findByRole(roleName).orElseGet(() -> {
                    Role role = new Role();
                    role.setRole(roleName);
                    return roleRepo.save(role);
                })
        );
    }
}
