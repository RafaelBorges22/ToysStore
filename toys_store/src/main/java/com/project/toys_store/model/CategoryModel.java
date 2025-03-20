package com.project.toys_store.model;

import jakarta.persistence.*;

import java.io.Serializable;

// termine de implementar essa classe rafael ou vitor, talvez ambos, implementem ela juntos
// criem todo o fluxo desde essa classe o repository até o controller
// o serializable transforma a classe em uma sequência de bits, facilitando o seu tráfego na rede, deixando a classe mais leve
// deve ter um conjunto de produtos -> pois não podemos ter produtos repetidos dentro dessa tabela
@Entity
@Table(name = "tb_category")
public class CategoryModel implements Serializable {
    // -> precisei colocar esse atributo para rodar o jpa e subir o banco de dados, caso contrário a aplicação quebraria todas as vezes que eu fosse testar
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
