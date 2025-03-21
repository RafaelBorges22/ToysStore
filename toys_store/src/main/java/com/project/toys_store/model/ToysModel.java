package com.project.toys_store.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "tb_toys")
public class ToysModel implements Serializable {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    // -> valores double são valores que tem apenas dois valores após o ponto, acredito ser mais pertinente colocar esses valores
    private Double price;

    public ToysModel(Long id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ToysModel toysModel = (ToysModel) o;
        return Objects.equals(id, toysModel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
