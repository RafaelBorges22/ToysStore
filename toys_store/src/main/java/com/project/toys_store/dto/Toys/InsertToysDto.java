package com.project.toys_store.dto.Toys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class InsertToysDto implements Serializable {
    private String name;
    private String description;
    private Double price;
    private Long categoryId;

    @Getter
    private List<MultipartFile> files = new ArrayList<>();

    public InsertToysDto(String name, String description, Double price, Long categoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
    }
}
