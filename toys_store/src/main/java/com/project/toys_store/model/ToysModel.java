package com.project.toys_store.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_toys")
public class ToysModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;

    @JsonIgnore
    @JoinColumn(name = "category_id")
    @ManyToOne
    private CategoryModel categoryId;

    @JsonIgnore
    @OneToMany(mappedBy = "toysModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhotosModel> photos = new ArrayList<>();
}
