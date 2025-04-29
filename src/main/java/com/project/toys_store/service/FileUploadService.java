package com.project.toys_store.service;

import com.project.toys_store.config.FileStorageProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class FileUploadService {
    private final Path fileStorageLocation;

    public FileUploadService(FileStorageProperties fileStorageLocation) {
        this.fileStorageLocation = Paths.get(fileStorageLocation.getUploadDir()).toAbsolutePath().normalize();
    }

    public String uploadFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        if (fileName.contains("..")) {
            throw new RuntimeException("Nome de arquivo inválido: " + fileName);
        }
        try {
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            file.transferTo(targetLocation);
            return ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/images/").path(fileName).toUriString();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
