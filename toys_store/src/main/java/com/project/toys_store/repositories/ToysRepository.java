package com.project.toys_store.repositories;


import com.project.toys_store.dto.Toys.ToysDto;
import com.project.toys_store.model.ToysModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ToysRepository extends JpaRepository<ToysModel, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM tb_toys where tb_toys.categoryId = :categoryId")
    List<ToysModel> findByCategoryId(@Param("categoryId") Long categoryId);

    @Query(nativeQuery = true, value = "SELECT t.id as id, t.name as name, t.description as description, t.price as price, p.path as filesPath FROM tb_toys t JOIN tb_photos p ON t.id = p.toy_id")
    List<ToysModel> findAllToys();
}