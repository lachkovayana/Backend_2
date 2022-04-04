package ru.tsu.hits.springdb1.dto.converter;

import ru.tsu.hits.springdb1.dto.CreateUpdateProjectDto;
import ru.tsu.hits.springdb1.dto.ProjectDto;
import ru.tsu.hits.springdb1.entity.ProjectEntity;

import java.util.UUID;

public class ProjectDtoConverter {

    public static ProjectEntity convertDtoToEntity(CreateUpdateProjectDto dto){
        ProjectEntity entity = new ProjectEntity();

        entity.setUuid(UUID.randomUUID().toString());
        entity.setCreationDate(dto.getCreationDate());
        entity.setEditDate(dto.getEditDate());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        return entity;
    }

    public static ProjectDto convertEntityToDto(ProjectEntity entity){
        ProjectDto dto = new ProjectDto();

        dto.setId(entity.getUuid());
        dto.setCreationDate(entity.getCreationDate());
        dto.setEditDate(entity.getEditDate());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());

        return dto;
    }

}
