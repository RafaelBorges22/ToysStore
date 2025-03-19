package com.project.toys_store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.toys_store.model.ToysModel;
import com.project.toys_store.service.ToysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")

public class ToysController {
    @Autowired
    private ToysService toysService;

    @GetMapping("/")
    public String toysList() {
        return toysService.toysList();
    }

    @PostMapping("/")
    public String toysPost(@RequestBody ToysModel body) {
        return toysService.ToysPost(body);
    }

    @PutMapping("/{name}")
    public String toysUp(@PathVariable String name, @RequestBody ToysModel updatedToy) {
        return toysService.ToysUp(name, updatedToy);
    }

    @DeleteMapping("/{name}")
    public String toysDel(@PathVariable String name) {
        return toysService.ToysDel(name);
    }
}
