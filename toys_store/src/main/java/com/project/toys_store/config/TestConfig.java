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

import java.util.Arrays;

// classe para fazer seed no banco de dados -> iniciar o banco de dados com alguns registros
@Configuration
@Profile("test") // -> prefil também definido no applicatio.properties
public class TestConfig implements CommandLineRunner {

    @Autowired
    private ToysRepository toysRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository  categoryRepository;

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

        CategoryModel category1 = new CategoryModel(null,"ToysModels Educativos");
        CategoryModel category2 = new CategoryModel(null, "Bonecas e Acessórios");
        CategoryModel category3 = new CategoryModel(null, "Carrinhos e Veículos");
        CategoryModel category4 = new CategoryModel(null, "Jogos e Quebra-Cabeças");
        CategoryModel category5 = new CategoryModel(null, "Bichos de Pelúcia");

        this.categoryRepository.saveAll(Arrays.asList(category1, category2, category3, category4, category5));



/*        ToysModel abaco = new ToysModel(null, "Ábaco Colorido", "EducaToysModels", 5, 39.90, category1);
        ToysModel tabletInfantil = new ToysModel(null, "Tablet Educativo", "TechKids", 4, 199.90, category1);
        ToysModel letrasMagneticas = new ToysModel(null, "Letras Magnéticas", "AprenderBrincando", 3, 29.90, category1);

        ToysModel bonecaBebe = new ToysModel(null, "Boneca Bebê Chorão", "BabyDoll", 3, 89.90, category2);
        ToysModel kitMaquiagem = new ToysModel(null, "Kit Maquiagem Infantil", "PrettyGirl", 5, 49.90, category2);
        ToysModel casaBoneca = new ToysModel(null, "Casa de Bonecas 3 Andares", "DreamHouse", 6, 299.90, category2);

        ToysModel carroControleRemoto = new ToysModel(null, "Carro Controle Remoto", "SpeedRacer", 6, 129.90, category3);
        ToysModel pistaHotWheels = new ToysModel(null, "Pista Hot Wheels", "Mattel", 4, 159.90, category3);
        ToysModel caminhaoBombeiros = new ToysModel(null, "Caminhão de Bombeiros", "ToyCars", 3, 79.90, category3);

        ToysModel xadrezMadeira = new ToysModel(null, "Xadrez de Madeira", "ClassicGames", 8, 69.90, category4);
        ToysModel quebraCabeca1000 = new ToysModel(null, "Quebra-Cabeça 1000 Peças", "PuzzleMaster", 10, 59.90, category4);
        ToysModel jogoMemoria = new ToysModel(null, "Jogo da Memória Animais", "MindKids", 4, 34.90, category4);

        ToysModel ursoPardo = new ToysModel(null, "Urso Pardo Grande", "SoftToys", 0, 79.90, category5);
        ToysModel coelhoBranco = new ToysModel(null, "Coelho Branco Macio", "CuddlyFriends", 0, 49.90, category5);
        ToysModel dinossauroVerde = new ToysModel(null, "Dinossauro Verde", "PrehistoricPets", 3, 64.90, category5);

        this.toysRepository.saveAll(Arrays.asList(
                abaco, tabletInfantil, letrasMagneticas,
                bonecaBebe, kitMaquiagem, casaBoneca,
                carroControleRemoto, pistaHotWheels, caminhaoBombeiros,
                xadrezMadeira, quebraCabeca1000, jogoMemoria,
                ursoPardo, coelhoBranco, dinossauroVerde
        ));*/
    }
}
