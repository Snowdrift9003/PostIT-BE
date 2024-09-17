package com.mazur.postit.mapper;

import com.mazur.postit.db.entity.NoteEntity;
import com.mazur.postit.dto.InputNoteDto;
import com.mazur.postit.dto.OutputNoteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.List;

@Mapper
public interface NoteMapper {
    @Named("trim")
    static String trim(final String value) {
        return value != null ? value.trim() : null;
    }

    OutputNoteDto mapToDto(NoteEntity entity);

    List<OutputNoteDto> mapToDto(Collection<NoteEntity> entities);

    @Mapping(target = "content", qualifiedByName = "trim")
    NoteEntity mapToEntity(InputNoteDto dto);

    @Mapping(target = "content", qualifiedByName = "trim")
    void updateFromDto(InputNoteDto dto, @MappingTarget NoteEntity entity);
}
