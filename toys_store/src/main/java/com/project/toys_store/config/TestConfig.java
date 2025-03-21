package com.project.toys_store.config;

import com.project.toys_store.model.ToysModel;
import com.project.toys_store.model.UserModel;
import com.project.toys_store.repositories.ToysRepository;
import com.project.toys_store.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.Arrays;

// classe para fazer seed no banco de dados -> iniciar o banco de dados com alguns registros
@Configuration
@Profile("test") // -> prefil também definido no applicatio.properties
public class TestConfig implements CommandLineRunner {

    @Autowired
    private ToysRepository toysRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        this.toysRepository.deleteAll();
        ToysModel toy1 = new ToysModel(null, "Carro de Controle Remoto", "Um carro vermelho com controle remoto e luzes LED.", 59.99);
        ToysModel toy2 = new ToysModel(null, "Boneca Fashion", "Boneca com roupas estilosas e acessórios.", 29.99);
        ToysModel toy3 = new ToysModel(null, "Jogo de Tabuleiro - Aventura Espacial", "Explore galáxias e conquiste planetas neste emocionante jogo.", 39.99);
        ToysModel toy4 = new ToysModel(null, "Kit de Blocos de Montar", "100 peças coloridas para construir o que quiser.", 24.99);
        ToysModel toy5 = new ToysModel(null, "Pelúcia do Leão", "Um leão de pelúcia macio e fofinho para abraçar.", 19.99);
        this.toysRepository.saveAll(Arrays.asList(toy1, toy2, toy3, toy4, toy5));

        UserModel user1 = new UserModel("João Silva", "joao.silva@example.com", "senha123");
        UserModel user2 = new UserModel("Maria Oliveira", "maria.oliveira@example.com", "senha456");
        UserModel user3 = new UserModel("Carlos Souza", "carlos.souza@example.com", "senha789");
        UserModel user4 = new UserModel("Ana Costa", "ana.costa@example.com", "senha101");
        UserModel user5 = new UserModel("Pedro Rocha", "pedro.rocha@example.com", "senha202");

        this.userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));
    }
}
