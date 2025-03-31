package com.project.toys_store.service;

import com.project.toys_store.dto.Toys.InsertToysDto;
import com.project.toys_store.dto.Toys.ToysDto;
import com.project.toys_store.exceptions.EntityNotFoundException;
import com.project.toys_store.model.PhotosModel;
import com.project.toys_store.model.ToysModel;
import com.project.toys_store.repositories.PhotosRepository;
import com.project.toys_store.repositories.ToysRepository;
import io.github.cdimascio.dotenv.DotEnvException;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ToysService {
    @Autowired
    private ToysRepository toysRepository;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private PhotosRepository photosRepository;

    public List<ToysDto> findAll() {
        List<ToysModel> toysModelList = this.toysRepository.findAllToys();
        return toysModelList.stream().map(item -> {
            ToysDto toysDto = new ToysDto();
            toysDto.setId(item.getId());
            toysDto.setName(item.getName());
            toysDto.setDescription(item.getDescription());
            toysDto.setPrice(item.getPrice());

           for(PhotosModel i : item.getPhotos()){
               toysDto.getFilesPath().add(i.getPath());
           }

            return toysDto;
        }).collect(Collectors.toList());
    }

    public List<ToysModel> findByCategoryId(Long categoryId) {
        return this.toysRepository.findByCategoryId(categoryId);
    }

    public ToysDto create(InsertToysDto createToy) {
        ToysModel toysModel = new ToysModel();
        this.dtoToEntity(createToy, toysModel);

        toysModel = this.toysRepository.save(toysModel);

        for (MultipartFile m : createToy.getFiles()) {
            String filePath = this.fileUploadService.uploadFile(m);
            PhotosModel photos = new PhotosModel(null, filePath, toysModel); // Associe o toysModel
            this.photosRepository.save(photos);
            toysModel.getPhotos().add(photos);
        }

        ToysDto toysDto = new ToysDto();
        toysDto.setId(toysModel.getId());
        toysDto.setName(toysModel.getName());
        toysDto.setDescription(toysModel.getDescription());
        toysDto.setPrice(toysModel.getPrice());

        for (PhotosModel photo : toysModel.getPhotos()) {
            toysDto.getFilesPath().add(photo.getPath());
        }

        return toysDto;
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

    public void deleteMany(Set<Long> ids) {
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

    public void dtoToEntity(InsertToysDto toysDto, ToysModel toysModel) {
        // transformar um dto em uma entity
        toysModel.setName(toysDto.getName());
        toysModel.setDescription(toysDto.getDescription());
        toysModel.setPrice(toysDto.getPrice());

    }
}