package com.project.toys_store.controller;

import com.project.toys_store.model.UserModel;
import com.project.toys_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserModel>> findAll() {
        List<UserModel> userModelList = this.userService.findAll();
        return ResponseEntity.ok().body(userModelList);
    }

    @PostMapping
    public ResponseEntity<UserModel> create(@RequestBody UserModel createUser) { // Corrigido: nome da variável
        createUser = this.userService.create(createUser);
        URI uriBuilder = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createUser.getId()).toUri();
        return ResponseEntity.created(uriBuilder).body(createUser);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserModel> update(@PathVariable Long id, @RequestBody UserModel updatedUser) {
        updatedUser = this.userService.updateUser(id, updatedUser);
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable Long id) {
        this.userService.deleteOne(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletemany")
    public ResponseEntity<Void> deleteMany(@RequestBody List<Long> ids) {
        this.userService.deleteMany(ids);
        return ResponseEntity.noContent().build();
    }
}