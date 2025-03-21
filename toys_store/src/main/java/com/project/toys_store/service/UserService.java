package com.project.toys_store.service;

import com.project.toys_store.exceptions.EntityNotFoundException;
import com.project.toys_store.model.UserModel;
import com.project.toys_store.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserModel> findAll() {
        return this.userRepository.findAll();
    }

    public UserModel create(UserModel createUser) {
        return this.userRepository.save(createUser);
    }

    public UserModel findOne(Long id) {
        Optional<UserModel> userModel = this.userRepository.findById(id);
        return userModel.orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
    }

    public UserModel updateUser(Long id, UserModel userModel) {
        try {
            UserModel userEntity = this.userRepository.getReferenceById(id);
            this.updateData(userEntity, userModel);
            return this.userRepository.save(userEntity);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Não encontrado");
        }
    }

    public void deleteOne(Long id) {
        try {
            this.userRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("não encontrado");
        }
    }

    public void deleteMany(List<Long> ids) {
        try {
            this.userRepository.deleteAllById(ids);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("não encontrado");
        }
    }

    public void updateData(UserModel userEntity, UserModel userModel) {
        userEntity.setName(userModel.getName());
        userEntity.setEmail(userModel.getEmail());
        userEntity.setPassword(userModel.getPassword());
    }
}