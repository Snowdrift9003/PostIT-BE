package com.mazur.postit.service;

import com.mazur.postit.controller.exception.PostItException;
import com.mazur.postit.db.entity.NoteEntity;
import com.mazur.postit.db.repository.NoteRepository;
import com.mazur.postit.dto.InputNoteDto;
import com.mazur.postit.mapper.NoteMapper;
import com.mazur.postit.mapper.NoteMapperImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = {NoteService.class, NoteMapperImpl.class})
class NoteServiceTest {

    @Autowired
    private NoteService noteService;
    @MockBean
    private NoteRepository noteRepository;
    @Autowired
    private NoteMapper noteMapper;
    @Captor
    private ArgumentCaptor<NoteEntity> noteEntityArgumentCaptor;

    @Test
    void getNoteNotFound() {
        var uuid = UUID.randomUUID();

        when(noteRepository.findById(uuid)).thenReturn(Optional.empty());
        var postItException = assertThrows(PostItException.class, () -> noteService.getNote(uuid));
        assertInstanceOf(PostItException.class, postItException);
        assertEquals(postItException.getErrors().size(), 1);
        assertEquals(postItException.getErrors().iterator().next(), "Note not found");
    }

    @Test
    void updateNoteNotFound() {
        var uuid = UUID.randomUUID();

        when(noteRepository.findById(uuid)).thenReturn(Optional.empty());
        var postItException = assertThrows(PostItException.class, () -> noteService.updateNote(uuid, new InputNoteDto("note-content")));
        assertInstanceOf(PostItException.class, postItException);
        assertEquals(postItException.getErrors().size(), 1);
        assertEquals(postItException.getErrors().iterator().next(), "Note not found");
    }

    @Test
    void updateNoteRepositoryCalled() throws PostItException {
        var uuid = UUID.randomUUID();

        when(noteRepository.findById(uuid)).thenReturn(Optional.of(new NoteEntity()));
        noteService.updateNote(uuid, new InputNoteDto("note-updated-content"));
        verify(noteRepository, times(1)).save(noteEntityArgumentCaptor.capture());

        assertEquals("note-updated-content", noteEntityArgumentCaptor.getValue().getContent());
    }

    @Test
    void createNoteRepositoryCalled() {
        noteService.createNote(new InputNoteDto("note-new-content"));
        verify(noteRepository, times(1)).save(noteEntityArgumentCaptor.capture());

        assertEquals("note-new-content", noteEntityArgumentCaptor.getValue().getContent());
    }
}
