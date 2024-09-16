package com.mazur.postit.controller;

import com.mazur.postit.controller.exception.PostItException;
import com.mazur.postit.dto.InputNoteDto;
import com.mazur.postit.dto.OutputNoteDto;
import com.mazur.postit.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping
    public List<OutputNoteDto> getNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public OutputNoteDto getNote(@PathVariable UUID id) throws PostItException {
        return noteService.getNote(id);
    }

    @PostMapping
    public OutputNoteDto createNote(@Valid @RequestBody InputNoteDto dto) {
        return noteService.createNote(dto);
    }

    @PatchMapping("/{id}")
    public OutputNoteDto updateNote(@PathVariable UUID id, @Valid @RequestBody InputNoteDto dto) throws PostItException {
        return noteService.updateNote(id, dto);
    }
}
