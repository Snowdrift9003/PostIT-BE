package com.mazur.postit.controller.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.Set;

@Getter
@JsonIgnoreProperties("stackTrace")
public final class PostItException extends Exception {
    private final Set<String> errors;

    public PostItException(Set<String> errors) {
        this.errors = errors;
    }
}
