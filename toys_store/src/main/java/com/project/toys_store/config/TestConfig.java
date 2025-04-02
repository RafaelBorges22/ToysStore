package com.project.toys_store.config;

import com.project.toys_store.model.CategoryModel;
import com.project.toys_store.model.ToysModel;
import com.project.toys_store.model.UserModel;
import com.project.toys_store.repositories.CategoryRepository;
import com.project.toys_store.repositories.ToysRepository;
import com.project.toys_store.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.*;

// classe para fazer seed no banco de dados -> iniciar o banco de dados com alguns registros
@Configuration
@Profile("test") // -> prefil também definido no applicatio.properties
public class TestConfig implements CommandLineRunner {

    @Autowired
    private ToysRepository toysRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        List<ToysModel> toysModelList = new ArrayList<>();

        CategoryModel category1 = new CategoryModel(null, "Educativos", toysModelList);
        CategoryModel category2 = new CategoryModel(null, "Bonecas e Acessórios", toysModelList);
        CategoryModel category3 = new CategoryModel(null, "Carrinhos e Veículos", toysModelList);
        CategoryModel category4 = new CategoryModel(null, "Jogos e Quebra-Cabeças", toysModelList);
        CategoryModel category5 = new CategoryModel(null, "Bichos de Pelúcia", toysModelList);
        this.categoryRepository.saveAll(Arrays.asList(category1, category2, category3, category4, category5));

        UserModel user1 = new UserModel(null, "João Silva", "joao.silva@example.com", "senha123", null);
        UserModel user2 = new UserModel(null, "Maria Oliveira", "maria.oliveira@example.com", "senha456", null);
        UserModel user3 = new UserModel(null, "Carlos Souza", "carlos.souza@example.com", "senha789", null);
        UserModel user4 = new UserModel(null, "Ana Costa", "ana.costa@example.com", "senha101", null);
        UserModel user5 = new UserModel(null, "Pedro Rocha", "pedro.rocha@example.com", "senha202", null);
        this.userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));

        /*
        ToysModel Carrinho = new ToysModel("Carro de Controle Remoto", "Carro vermelho com controle", 59.99);
        Carrinho.setCategory(category3);
        ToysModel Boneca = new ToysModel("Boneca Fashion", "Boneca com roupas estilosas", 29.99);
        Boneca.setCategory(category2);
        ToysModel Tabuleiro = new ToysModel("Jogo de Tabuleiro", "Jogo de aventura espacial", 39.99);
        Tabuleiro.setCategory(category1);
        ToysModel Xadrez = new ToysModel("Tabuleiro de Xadrez", "Jogo de estrategia, muito RedTrad", 39.99);
        Xadrez.setCategory(category1);
        toysRepository.saveAll(Arrays.asList(Carrinho, Boneca, Tabuleiro, Xadrez));
         */
    }
}
