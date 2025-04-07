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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @JsonIgnore
    @JoinColumn(name = "category_id", nullable = false)
    @ManyToOne
    private CategoryModel categoryId;

    @JsonIgnore
    @OneToMany(mappedBy = "toysmodel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhotosModel> photos = new ArrayList<>();
}
