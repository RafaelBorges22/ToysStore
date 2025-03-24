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
        List<CategoryModel> categories = this.categoryService.findAll();
        return ResponseEntity.ok().body(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryModel> create(@RequestBody CategoryModel createCategory) {
        createCategory = this.categoryService.create(createCategory);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createCategory.getId())
                .toUri();
        return ResponseEntity.created(uri).body(createCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryModel> findOne(@PathVariable Long id) {
        CategoryModel category = this.categoryService.findOne(id);
        return ResponseEntity.ok().body(category);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryModel> update(@PathVariable Long id, @RequestBody CategoryModel updatedCategory) {
        updatedCategory = this.categoryService.updateCategory(id, updatedCategory);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable Long id) {
        this.categoryService.deleteOne(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletemany")
    public ResponseEntity<Void> deleteMany(@RequestBody List<Long> ids) {
        this.categoryService.deleteMany(ids);
        return ResponseEntity.noContent().build();
    }
}