package com.project.toys_store.repositories;


import com.project.toys_store.model.ToysModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToysRepository extends JpaRepository<ToysModel, Long> {
    List<ToysModel> findByCategoriaId(Long categoriaId);
}
