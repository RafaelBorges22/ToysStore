package com.project.toys_store.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "tb_toys")
public class ToysModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;

    public ToysModel() {
    }

    public ToysModel(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryModel category;

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
