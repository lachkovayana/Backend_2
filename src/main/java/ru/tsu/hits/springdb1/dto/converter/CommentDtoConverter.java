package ru.tsu.hits.springdb1.dto.converter;

import ru.tsu.hits.springdb1.dto.CommentDto;
import ru.tsu.hits.springdb1.dto.CreateUpdateCommentDto;
import ru.tsu.hits.springdb1.dto.TaskDto;
import ru.tsu.hits.springdb1.entity.CommentEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommentDtoConverter {
    public static CommentEntity convertCommentDtoToEntity(CreateUpdateCommentDto dto, UserEntity user, List<TaskEntity> tasks) {
        CommentEntity entity = new CommentEntity();

        entity.setUuid(UUID.randomUUID().toString());
        entity.setCreationDate(dto.getCreationDate());
        entity.setEditDate(dto.getEditDate());
        entity.setCommentText(dto.getCommentText());
        entity.setAuthor(user);
        entity.setTasks(tasks);
        return entity;
    }

    public static CommentDto convertCommentEntityToDto(CommentEntity entity) {
        CommentDto dto = new CommentDto();

        dto.setId(entity.getUuid());
        dto.setCreationDate(entity.getCreationDate());
        dto.setCommentText(entity.getCommentText());
        dto.setEditDate(entity.getEditDate());
        dto.setAuthor(entity.getAuthor().getFullName());

        return dto;
    }

    public static List<CommentDto> convertCommentEntitiesToDto(List<CommentEntity> entities) {
        List<CommentDto> result = new ArrayList<>();
        entities.forEach(entity -> {
            result.add(convertCommentEntityToDto(entity));
        });
        return result;
    }
}
