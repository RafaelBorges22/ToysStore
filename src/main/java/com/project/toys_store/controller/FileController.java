package com.project.toys_store.controller;

import com.project.toys_store.config.FileStorageProperties;
import com.project.toys_store.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    private FileUploadService fileUploadService;

    private final Path fileStorageLocation;

    public FileController(FileStorageProperties fileStorageLocation) {
        this.fileStorageLocation = Paths.get(fileStorageLocation.getUploadDir()).toAbsolutePath().normalize();
    }


    @PostMapping("/upload/test")
    public ResponseEntity<String> upload(@RequestBody MultipartFile file) {
        String filePath = this.fileUploadService.uploadFile(file);

        return ResponseEntity.ok().body(filePath);
    }


    @GetMapping("/images/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
