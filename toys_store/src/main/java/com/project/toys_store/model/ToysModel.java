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
@AllArgsConstructor
@Table(name = "tb_toys")
public class ToysModel implements Serializable {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryModel categoryModel;


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
