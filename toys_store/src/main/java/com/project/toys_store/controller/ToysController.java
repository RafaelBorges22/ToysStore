package com.project.toys_store.controller;

import com.project.toys_store.dto.Toys.InsertToysDto;
import com.project.toys_store.dto.Toys.ToysDto;
import com.project.toys_store.model.ToysModel;
import com.project.toys_store.service.ToysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/toys")
public class ToysController {
    @Autowired
    private ToysService toysService;

    @GetMapping
    public ResponseEntity<List<ToysDto>> findAll() {
        List<ToysDto> toysModelList = this.toysService.findAll();
        return ResponseEntity.ok().body(toysModelList);
    }

    //Get por categoria
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ToysModel>> getBrinquedosByCategoria(@PathVariable Long categoryId) {
        List<ToysModel> toysModelList = toysService.findByCategoryId(categoryId);
        return ResponseEntity.ok().body(toysModelList);
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ToysDto> create(InsertToysDto createToy) {
        ToysDto toy = this.toysService.create(createToy);
        URI uriBuilder = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(toy.getId()).toUri();
        return ResponseEntity.created(uriBuilder).body(toy);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToysModel> findOne(@PathVariable Long id) {
        ToysModel toysModel = this.toysService.findOne(id);
        return ResponseEntity.ok().body(toysModel);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ToysModel> update(@PathVariable Long id, @RequestBody ToysModel updatedToy) {
        updatedToy = this.toysService.updateToy(id, updatedToy);
        return ResponseEntity.ok().body(updatedToy);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable Long id) {
        this.toysService.deleteOne(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletemany")
    public ResponseEntity<Void> deleteMany(@RequestBody Set<Long> ids) {
        this.toysService.deleteMany(ids);
        return ResponseEntity.noContent().build();
    }
}