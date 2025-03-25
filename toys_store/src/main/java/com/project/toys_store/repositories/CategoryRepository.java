package com.project.toys_store.repositories;
import com.project.toys_store.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
    boolean existsByName(String name);
}
