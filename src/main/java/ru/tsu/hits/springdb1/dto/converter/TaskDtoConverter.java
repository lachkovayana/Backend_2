package ru.tsu.hits.springdb1.dto.converter;

import ru.tsu.hits.springdb1.dto.CreateUpdateTaskDto;
import ru.tsu.hits.springdb1.dto.TaskDto;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskDtoConverter {

    public static TaskEntity convertDtoToEntity(CreateUpdateTaskDto dto, ProjectEntity projectEntity, UserEntity creator, UserEntity editor) {

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setUuid(UUID.randomUUID().toString());
        taskEntity.setCreationDate(dto.getCreationDate());
        taskEntity.setEditDate(dto.getEditDate());
        taskEntity.setTitle(dto.getTitle());
        taskEntity.setDescription(dto.getDescription());
        taskEntity.setTimeEstimate(dto.getTimeEstimate());
        taskEntity.setPriority(dto.getPriority());

        //---------------
        taskEntity.setProject(projectEntity);
        taskEntity.setCreator(creator);
        taskEntity.setEditor(editor);

        return taskEntity;
    }

    public static TaskDto convertEntityToDto(TaskEntity entity) {
        TaskDto dto = new TaskDto();

        dto.setId(entity.getUuid());
        dto.setCreationDate(entity.getCreationDate());
        dto.setEditDate(entity.getEditDate());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setTimeEstimate(entity.getTimeEstimate());
        dto.setPriority(entity.getPriority());

        //---------------
        dto.setCreator(entity.getCreator().getFullName());
        dto.setEditor(entity.getEditor().getFullName());
        dto.setProject(entity.getProject().getName());

        return dto;
    }
    public static List<TaskDto> convertTasksToDto(List<TaskEntity> taskEntities){
        List<TaskDto> tasks = new ArrayList<>();
        taskEntities.forEach(element -> {
            TaskDto taskDto = new TaskDto();

            taskDto.setId(element.getUuid());
            taskDto.setCreationDate(element.getCreationDate());
            taskDto.setEditDate(element.getEditDate());
            taskDto.setTitle(element.getTitle());
            taskDto.setDescription(element.getDescription());
            taskDto.setPriority(element.getPriority());
            taskDto.setTimeEstimate(element.getTimeEstimate());

            taskDto.setProject(element.getProject().getName());
            taskDto.setCreator(element.getCreator().getFullName());
            taskDto.setEditor(element.getEditor().getFullName());

            tasks.add(taskDto);
        });

        return tasks;
    }
}
