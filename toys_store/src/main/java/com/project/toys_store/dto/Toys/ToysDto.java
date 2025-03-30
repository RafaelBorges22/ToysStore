package com.project.toys_store.dto.Toys;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ToysDto {
    private Long id;

    private String name;
    private String description;
    private Double price;

    @Getter
    private Set<String> filesPath = new HashSet<>();

    public ToysDto(String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
