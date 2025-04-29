package com.project.toys_store.service;

import com.project.toys_store.dto.User.InsertUserDTo;
import com.project.toys_store.dto.User.UserDTo;
import com.project.toys_store.enums.UserRole;
import com.project.toys_store.exceptions.EntityNotFoundException;
import com.project.toys_store.model.UserModel;
import com.project.toys_store.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final FileUploadService fileUploadService;

    public List<UserModel> findAll() {
        return this.userRepository.findAll();
    }

    public UserModel create(InsertUserDTo createUser) {
        UserModel userModel = new UserModel();
        this.dtoToEntity(userModel, createUser);
        String file = this.fileUploadService.uploadFile(createUser.getPhoto());
        userModel.setFilePath(file);
        return this.userRepository.save(userModel);
    }

    public UserModel findOne(Long id) {
        Optional<UserModel> userModel = this.userRepository.findById(id);
        return userModel.orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
    }

    public UserDTo updateUser(Long id, InsertUserDTo insertUserDTo) {
        UserModel userModel = new UserModel();
        // fazer o upload da nova imagem(se ela existir)
        if(!insertUserDTo.getPhoto().isEmpty()){
            String path = this.fileUploadService.uploadFile(insertUserDTo.getPhoto());
            userModel.setFilePath(path);
        }
        this.dtoToEntity(userModel, insertUserDTo);
        try {
            UserModel userEntity = this.userRepository.getReferenceById(id);
            this.updateData(userEntity, userModel);
            this.userRepository.save(userModel);

            return new UserDTo(userModel);
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

    public void dtoToEntity(UserModel userModel, InsertUserDTo insertUserDTo) {
        UserRole userRole = UserRole.fromValue(insertUserDTo.getUserRole().getValue());
        userModel.setName(insertUserDTo.getName());
        userModel.setEmail(insertUserDTo.getEmail());
        userModel.setPassword(insertUserDTo.getPassword());
        userModel.setUserRole(userRole);
    }
}