package com.project.toys_store.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class StandardController {

    @GetMapping
    public ResponseEntity<String> standard(){
        return ResponseEntity.ok().body("Funfando legal!");
    }
}
