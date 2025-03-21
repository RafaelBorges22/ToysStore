package com.project.toys_store.repositories;


import com.project.toys_store.model.ToysModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToysRepository extends JpaRepository<ToysModel, Long> {
}
