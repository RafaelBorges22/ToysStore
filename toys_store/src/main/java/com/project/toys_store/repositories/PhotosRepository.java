package com.project.toys_store.repositories;

import com.project.toys_store.model.PhotosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhotosRepository extends JpaRepository<PhotosModel, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM tb_photos WHERE tb_photos.toy_id = :toyId")
    List<PhotosModel> findByToyId(@Param("toyId") Long toyId);
}
