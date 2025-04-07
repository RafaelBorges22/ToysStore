package com.project.toys_store.config;

import com.project.toys_store.enums.UserRole;
import com.project.toys_store.model.CategoryModel;
import com.project.toys_store.model.ToysModel;
import com.project.toys_store.model.UserModel;
import com.project.toys_store.repositories.CategoryRepository;
import com.project.toys_store.repositories.ToysRepository;
import com.project.toys_store.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.*;

// classe para fazer seed no banco de dados -> iniciar o banco de dados com alguns registros
@Configuration
@Profile("test") // -> prefil também definido no applicatio.properties
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

    private final ToysRepository toysRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        List<ToysModel> toysModelList = new ArrayList<>();

        CategoryModel category1 = new CategoryModel(null, "Educativos", "brinquedos educativos", toysModelList);
        CategoryModel category2 = new CategoryModel(null, "Bonecas e Acessórios", "bonecas e acessórios separados", toysModelList);
        CategoryModel category3 = new CategoryModel(null, "Carrinhos e Veículos", "carrinhos de controle remoto", toysModelList);
        CategoryModel category4 = new CategoryModel(null, "Jogos e Quebra-Cabeças", "rpg's de mesa e jogos lógicos", toysModelList);
        CategoryModel category5 = new CategoryModel(null, "Bichos de Pelúcia", "não sei", toysModelList);
        this.categoryRepository.saveAll(Arrays.asList(category1, category2, category3, category4, category5));

        UserModel user1 = new UserModel(null, "João Silva", UserRole.CLIENT, "joao.silva@example.com", "senha123", null);
        UserModel user2 = new UserModel(null, "Maria Oliveira", UserRole.ADMIN, "maria.oliveira@example.com", "senha456", null);
        UserModel user3 = new UserModel(null, "Carlos Souza", UserRole.CLIENT, "carlos.souza@example.com", "senha789", null);
        UserModel user4 = new UserModel(null, "Ana Costa", UserRole.ADMIN, "ana.costa@example.com", "senha101", null);
        UserModel user5 = new UserModel(null, "Pedro Rocha", UserRole.ADMIN, "pedro.rocha@example.com", "senha202", null);
        this.userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));


        ToysModel Carrinho = new ToysModel(null, "Carro de Controle Remoto", "Carro vermelho com controle", 59.99, category3, new ArrayList<>());
        ToysModel Boneca = new ToysModel(null, "Boneca Fashion", "Boneca com roupas estilosas", 29.99, category2, new ArrayList<>());
        ToysModel Tabuleiro = new ToysModel(null, "Jogo de Tabuleiro", "Jogo de aventura espacial", 39.99, category1, new ArrayList<>());
        ToysModel Xadrez = new ToysModel(null, "Tabuleiro de Xadrez", "Jogo de estrategia, muito RedTrad", 39.99, category1, new ArrayList<>());
        toysRepository.saveAll(Arrays.asList(Carrinho, Boneca, Tabuleiro, Xadrez));
    }
}
