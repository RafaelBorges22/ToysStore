package com.project.toys_store.controller.exceptions;

import com.project.toys_store.exceptions.AlreadyExistsException;
import com.project.toys_store.exceptions.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<StandardError> alreaduExists(AlreadyExistsException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new StandardError(
                        e.getMessage(),
                        HttpStatus.CONFLICT.value(),
                        LocalDateTime.now(),
                        request.getRequestURI()
                )
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> notFound(EntityNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new StandardError(
                        e.getMessage(),
                        HttpStatus.NOT_FOUND.value(),
                        LocalDateTime.now(),
                        request.getRequestURI()
                )
        );
    }
}
