package com.mazur.postit.mapper;

import com.mazur.postit.db.entity.NoteEntity;
import com.mazur.postit.dto.InputNoteDto;
import org.mapstruct.Mapper;

@Mapper
public interface NoteMapper {
    NoteEntity mapToEntity(InputNoteDto dto);
}
