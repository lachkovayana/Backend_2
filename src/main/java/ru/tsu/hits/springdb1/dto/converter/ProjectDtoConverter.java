package ru.tsu.hits.springdb1.dto.converter;

import ru.tsu.hits.springdb1.dto.CreateUpdateProjectDto;
import ru.tsu.hits.springdb1.dto.ProjectDto;
import ru.tsu.hits.springdb1.dto.TaskDto;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectDtoConverter {

    public static ProjectEntity convertProjectDtoToEntity(CreateUpdateProjectDto dto) {
        ProjectEntity entity = new ProjectEntity();

        entity.setUuid(UUID.randomUUID().toString());
        entity.setCreationDate(dto.getCreationDate());
        entity.setEditDate(dto.getEditDate());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        return entity;
    }

    public static ProjectDto convertProjectEntityToDto(ProjectEntity entity, List<TaskEntity> taskEntities) {
        ProjectDto dto = new ProjectDto();

        dto.setId(entity.getUuid());
        dto.setCreationDate(entity.getCreationDate());
        dto.setEditDate(entity.getEditDate());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setTasks(TaskDtoConverter.convertEntitiesToDtoWithoutComments(taskEntities));
        return dto;
    }


}
