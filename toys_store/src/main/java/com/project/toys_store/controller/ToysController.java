package com.project.toys_store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.toys_store.model.ToysModel;
import com.project.toys_store.service.ToysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

// observação -> sempre utiliza esses vocabulários
// findAll, findOne, create, deleteOne, deleteMany
// eles representam boas práticas

@RestController
@RequestMapping("/toys")

public class ToysController {
    @Autowired
    private ToysService toysService;

    @GetMapping
    public ResponseEntity<List<ToysModel>> findAll() {
        List<ToysModel> toysModelList = this.toysService.findAll();
        return ResponseEntity.ok().body(toysModelList);
    }

    //Get por categoria
    @GetMapping("/categoria/{categoriaId}")
    public List<ToysModel> getBrinquedosByCategoria(@PathVariable Long categoriaId) {
        return toysService.findByCategoriaId(categoriaId);
    }

    @PostMapping // -> retirei o ("/"), talve esse era o problema que você estav aefrentando...
    public ResponseEntity<ToysModel> create(@RequestBody ToysModel createToy) {
        createToy = this.toysService.create(createToy);
        URI uriBuilder = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createToy.getId()).toUri();
        return ResponseEntity.created(uriBuilder).body(createToy);
    }

    @PutMapping(value = "/{id}") // -> aqui você esqueceu de colocar o "value", talvez este era o erro
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
    public ResponseEntity<Void> deleteMany(@RequestBody List<Long> ids) {
        this.toysService.deleteMany(ids);
        return ResponseEntity.noContent().build();
    }
}
