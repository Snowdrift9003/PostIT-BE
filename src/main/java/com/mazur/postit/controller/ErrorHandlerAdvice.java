package com.mazur.postit.controller;

import com.mazur.postit.controller.exception.PostItException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandlerAdvice {

    @ExceptionHandler(PostItException.class)
    public ResponseEntity<PostItException> handleValidation(final PostItException ex) {
        return ResponseEntity.badRequest().body(ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<PostItException> handleValidation(final MethodArgumentNotValidException ex) {
        Set<String> validationErrors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toSet());
        return ResponseEntity.badRequest().body(new PostItException(validationErrors));
    }
}
