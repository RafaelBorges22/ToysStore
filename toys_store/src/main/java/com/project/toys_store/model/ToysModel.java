package com.project.toys_store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor

public class ToysModel {
    @Id
    private String name;
   // private String image;
   // private String description;
    private Float price;

    public ToysModel() {

    }
    // private String category_id;
}
