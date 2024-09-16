package com.mazur.postit.service;

import com.mazur.postit.controller.exception.PostItException;
import com.mazur.postit.db.repository.NoteRepository;
import com.mazur.postit.dto.InputNoteDto;
import com.mazur.postit.dto.OutputNoteDto;
import com.mazur.postit.mapper.NoteMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    public OutputNoteDto createNote(@Valid InputNoteDto dto) {
        var createdNote = noteRepository.save(noteMapper.mapToEntity(dto));
        return noteMapper.mapToDto(createdNote);
    }

    public List<OutputNoteDto> getAllNotes() {
        var notes = noteRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
        return noteMapper.mapToDto(notes);
    }

    public OutputNoteDto getNote(UUID id) throws PostItException {
        var noteEntity = noteRepository.findById(id).orElseThrow(() -> new PostItException(Set.of("Note not found")));
        return noteMapper.mapToDto(noteEntity);
    }

    public OutputNoteDto updateNote(UUID id, @Valid InputNoteDto dto) throws PostItException {
        var noteEntity = noteRepository.findById(id).orElseThrow(() -> new PostItException(Set.of("Note not found")));
        noteMapper.updateFromDto(dto, noteEntity);

        var updatedNote = noteRepository.save(noteEntity);
        return noteMapper.mapToDto(updatedNote);
    }

    public void deleteNote(UUID id) throws PostItException {
        var noteEntity = noteRepository.findById(id).orElseThrow(() -> new PostItException(Set.of("Note not found")));
        noteRepository.delete(noteEntity);
    }
}
