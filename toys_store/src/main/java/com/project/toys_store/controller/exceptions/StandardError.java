package com.project.toys_store.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class StandardError {
    private String message;
    private Integer status;
    private LocalDateTime timestamp;
    private String path;
}
