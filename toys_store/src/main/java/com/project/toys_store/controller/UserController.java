package com.project.toys_store.controller;

import com.project.toys_store.dto.User.InsertUserDTo;
import com.project.toys_store.dto.User.UserDTo;
import com.project.toys_store.model.UserModel;
import com.project.toys_store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserModel>> findAll() {
        List<UserModel> userModelList = this.userService.findAll();
        return ResponseEntity.ok().body(userModelList);
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<UserModel> create(InsertUserDTo createUser) {
        UserModel create = this.userService.create(createUser);
        URI uriBuilder = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(create.getId()).toUri();
        return ResponseEntity.created(uriBuilder).body(create);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTo> update(@PathVariable Long id, @RequestBody InsertUserDTo updatedUser) {
        UserDTo userDTo = this.userService.updateUser(id, updatedUser);
        return ResponseEntity.ok().body(userDTo);
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