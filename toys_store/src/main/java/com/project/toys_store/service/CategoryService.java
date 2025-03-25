package com.project.toys_store.service;

import com.project.toys_store.exceptions.EntityNotFoundException;
import com.project.toys_store.model.CategoryModel;
import com.project.toys_store.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Set<CategoryModel> findAll() {
        return new HashSet<>(this.categoryRepository.findAll());
    }

    public CategoryModel create(CategoryModel createCategory) {
        // Verifica se já existe uma categoria com o mesmo nome
        if (categoryRepository.existsByName(createCategory.getName())) {
            throw new IllegalArgumentException("Já existe uma categoria com este nome");
        }
        return this.categoryRepository.save(createCategory);
    }

    public CategoryModel findOne(Long id) {
        Optional<CategoryModel> categoryModel = this.categoryRepository.findById(id);
        return categoryModel.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }

    public CategoryModel updateCategory(Long id, CategoryModel categoryModel) {
        try {
            CategoryModel categoryEntity = this.categoryRepository.getReferenceById(id);

            // Verifica se o novo nome já existe (e não é o mesmo da entidade atual)
            if (!categoryEntity.getName().equals(categoryModel.getName()) &&
                    categoryRepository.existsByName(categoryModel.getName())) {
                throw new IllegalArgumentException("Já existe uma categoria com este nome");
            }

            this.updateData(categoryEntity, categoryModel);
            return this.categoryRepository.save(categoryEntity);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("not found");
        }
    }

    public void deleteOne(Long id) {
        try {
            this.categoryRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("not found");
        }
    }

    public void deleteMany(Set<Long> ids) {
        try {
            this.categoryRepository.deleteAllById(ids);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("not found");
        }
    }

    private void updateData(CategoryModel categoryEntity, CategoryModel categoryModel) {
        categoryEntity.setName(categoryModel.getName());
    }
}