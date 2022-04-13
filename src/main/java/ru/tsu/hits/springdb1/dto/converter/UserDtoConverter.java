package ru.tsu.hits.springdb1.dto.converter;

import ru.tsu.hits.springdb1.dto.CreateUpdateUserDto;
import ru.tsu.hits.springdb1.dto.UserDto;
import ru.tsu.hits.springdb1.entity.CommentEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;
import ru.tsu.hits.springdb1.passwordMethods;

import java.util.List;
import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;

public class UserDtoConverter {

    public static UserEntity convertDtoToEntity(CreateUpdateUserDto dto) {
        byte[] shaInBytes = passwordMethods.digest(dto.getPassword().getBytes(UTF_8), "SHA-256");
        UserEntity entity = new UserEntity();
        entity.setUuid(UUID.randomUUID().toString());
        entity.setCreationDate(dto.getCreationDate());
        entity.setEditDate(dto.getEditDate());
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setPassword(passwordMethods.bytesToHex(shaInBytes));
        entity.setRole(dto.getRole());

        return entity;
    }

    public static UserDto convertEntityToDto(UserEntity entity, List<TaskEntity> createdTaskEntities, List<TaskEntity> editedTaskEntities, List<CommentEntity> comments) {
        UserDto dto = new UserDto();

        dto.setId(entity.getUuid());
        dto.setCreationDate(entity.getCreationDate());
        dto.setEditDate(entity.getEditDate());
        dto.setFullName(entity.getFullName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());
        dto.setCreatedTasks(TaskDtoConverter.convertEntitiesToDtoWithoutComments(createdTaskEntities));
        dto.setEditedTasks(TaskDtoConverter.convertEntitiesToDtoWithoutComments(editedTaskEntities));
        dto.setComments(CommentDtoConverter.convertCommentEntitiesToDto(comments));
        return dto;
    }

    public static UserDto convertEntityToDtoWithoutLists(UserEntity entity) {
        UserDto dto = new UserDto();

        dto.setId(entity.getUuid());
        dto.setCreationDate(entity.getCreationDate());
        dto.setEditDate(entity.getEditDate());
        dto.setFullName(entity.getFullName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());

        return dto;
    }

    public static UserEntity convertCsvToEntity(CreateUpdateUserDto elem) {
        byte[] shaInBytes = passwordMethods.digest(elem.getPassword().getBytes(UTF_8), "SHA-256");
        UserEntity userEntity = new UserEntity();
        userEntity.setUuid(UUID.randomUUID().toString());
        userEntity.setCreationDate(elem.getCreationDate());
        userEntity.setEditDate(elem.getEditDate());
        userEntity.setFullName(elem.getFullName());
        userEntity.setEmail(elem.getEmail());
        userEntity.setPassword(passwordMethods.bytesToHex(shaInBytes));
        userEntity.setRole(elem.getRole());
        return userEntity;
    }

}
