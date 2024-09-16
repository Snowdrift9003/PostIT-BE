package com.mazur.postit.service;

import com.mazur.postit.db.repository.NoteRepository;
import com.mazur.postit.dto.InputNoteDto;
import com.mazur.postit.mapper.NoteMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    public void createNote(@Valid InputNoteDto dto) {
        noteRepository.save(noteMapper.mapToEntity(dto));
    }
}
