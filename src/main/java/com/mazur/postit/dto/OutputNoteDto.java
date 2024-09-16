package com.mazur.postit.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record OutputNoteDto(UUID id, LocalDateTime createDate, String content) {
}
