package ru.tsu.hits.springdb1.dto.converter;

import ru.tsu.hits.springdb1.dto.CreateUpdateTaskDto;
import ru.tsu.hits.springdb1.dto.TaskDto;
import ru.tsu.hits.springdb1.entity.TaskEntity;

import java.util.UUID;

public class TaskDtoConverter {

    public static TaskEntity converterDtoToEntity(CreateUpdateTaskDto dto) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setUuid(UUID.randomUUID().toString());
        taskEntity.setCreationDate(dto.getCreationDate());
        taskEntity.setEditDate(dto.getEditDate());
        taskEntity.setTitle(dto.getTitle());
        taskEntity.setDescription(dto.getDescription());
        taskEntity.setTimeEstimate(dto.getTimeEstimate());
        taskEntity.setPriority(dto.getPriority());
        taskEntity.setProject(dto.getProject());

        return taskEntity;
    }

//    public static TaskDto converterEntityToDto(TaskEntity entity, List<Pro>){}

}
