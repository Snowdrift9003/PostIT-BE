package com.mazur.postit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record InputNoteDto(@NotBlank @Size(max = 200) String content) {
}
