package com.mazur.postit.controller;

import com.mazur.postit.dto.InputNoteDto;
import com.mazur.postit.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @PostMapping
    public void createNote(@Valid @RequestBody InputNoteDto dto) {
        noteService.createNote(dto);
    }
}
