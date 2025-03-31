package com.project.toys_store.dto.Toys;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToysDto {
    private Long id;
    private String name;
    private String description;
    private Double price;

    @Getter
    private Set<String> filesPath = new HashSet<>();
}
