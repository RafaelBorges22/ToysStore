package com.project.toys_store.service;

import com.project.toys_store.exceptions.EntityNotFoundException;
import com.project.toys_store.model.ToysModel;
import com.project.toys_store.repositories.ToysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ToysService {
    @Autowired
    private ToysRepository toysRepository;

    public List<ToysModel> findAll() {
        return this.toysRepository.findAll();
    }

    public ToysModel create(ToysModel createToy) {
        return this.toysRepository.save(createToy);
    }

    public ToysModel findOne(Long id) {
        Optional<ToysModel> toysModel = this.toysRepository.findById(id);
        // a classe EntityNotFoundException precisa ser melhorada -> me lembre dessa tarefa
        return toysModel.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }

    public ToysModel updateToy(Long id, ToysModel toysModel) {
        try {
            ToysModel toysEntity = this.toysRepository.getReferenceById(id);
            this.updateData(toysEntity, toysModel);
            return this.toysRepository.save(toysEntity);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("not found");
        }
    }

    public void deleteOne(Long id) {
        try {
            this.toysRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("not found");
        }
    }

    public void deleteMany(List<Long> ids) {
        try {
            this.toysRepository.deleteAllById(ids);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("not found");
        }
    }

    public void updateData(ToysModel toysEntity, ToysModel toysModel) {
        toysEntity.setName(toysModel.getName());
        toysEntity.setDescription(toysModel.getDescription());
        toysEntity.setPrice(toysModel.getPrice());
    }
}
