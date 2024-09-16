package com.mazur.postit.mapper;

import com.mazur.postit.db.entity.NoteEntity;
import com.mazur.postit.dto.InputNoteDto;
import com.mazur.postit.dto.OutputNoteDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.List;

@Mapper
public interface NoteMapper {
    NoteEntity mapToEntity(InputNoteDto dto);

    OutputNoteDto mapToDto(NoteEntity entity);

    List<OutputNoteDto> mapToDto(Collection<NoteEntity> entities);

    void updateFromDto(InputNoteDto dto, @MappingTarget NoteEntity entity);
}
