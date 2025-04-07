package com.project.toys_store.repositories;


import com.project.toys_store.dto.Toys.ToysDto;
import com.project.toys_store.model.ToysModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ToysRepository extends JpaRepository<ToysModel, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM tb_toys where tb_toys.category_id = :categoryId")
    List<ToysModel> findAllByCategoryId(Long categoryId);
}