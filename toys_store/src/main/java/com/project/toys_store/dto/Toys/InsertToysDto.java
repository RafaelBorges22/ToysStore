package com.project.toys_store.dto.Toys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class InsertToysDto implements Serializable {
    private String name;
    private String description;
    private Double price;

    @Getter
    @JsonIgnore
    private Set<MultipartFile> files = new HashSet<>();

    public InsertToysDto(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
