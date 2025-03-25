package com.project.toys_store.controller;

import com.project.toys_store.model.CategoryModel;
import com.project.toys_store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryModel>> findAll() {
        List<CategoryModel> categories = categoryService.findAll();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryModel> findOne(@PathVariable Long id) {
        CategoryModel category = categoryService.findOne(id);
        return ResponseEntity.ok().body(category);
    }

    @PostMapping
    public ResponseEntity<CategoryModel> create(@RequestBody CategoryModel category) {
        CategoryModel createdCategory = categoryService.create(category);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCategory.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryModel> update(@PathVariable Long id, @RequestBody CategoryModel category) {
        CategoryModel updatedCategory = categoryService.updateCategory(id, category);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable Long id) {
        categoryService.deleteOne(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletemany")
    public ResponseEntity<Void> deleteMany(@RequestBody List<Long> ids) {
        categoryService.deleteMany(ids);
        return ResponseEntity.noContent().build();
    }
}