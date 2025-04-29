package com.project.toys_store.config;
import com.project.toys_store.enums.UserRole;
import com.project.toys_store.model.UserModel;
import com.project.toys_store.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@RequiredArgsConstructor
@Configuration
@Profile("development")
public class DevelopmentConfig implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        UserModel user1 = new UserModel(null, "João Silva", UserRole.CLIENT, "joao.silva@example.com", "senha123", null);
        UserModel user2 = new UserModel(null, "Maria Oliveira", UserRole.ADMIN, "maria.oliveira@example.com", "senha456", null);
        UserModel user3 = new UserModel(null, "Carlos Souza", UserRole.CLIENT, "carlos.souza@example.com", "senha789", null);
        UserModel user4 = new UserModel(null, "Ana Costa", UserRole.ADMIN, "ana.costa@example.com", "senha101", null);
        UserModel user5 = new UserModel(null, "Pedro Rocha", UserRole.ADMIN, "pedro.rocha@example.com", "senha202", null);
        this.userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));
    }
}
