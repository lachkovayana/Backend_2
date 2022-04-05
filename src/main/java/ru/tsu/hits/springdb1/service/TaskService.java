package ru.tsu.hits.springdb1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import ru.tsu.hits.springdb1.dto.CreateUpdateTaskDto;
import ru.tsu.hits.springdb1.dto.TaskDto;
import ru.tsu.hits.springdb1.dto.converter.TaskDtoConverter;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;
import ru.tsu.hits.springdb1.repository.TaskRepository;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    private final ProjectService projectService;
    private final UserService userService;

    @Transactional
    public TaskDto createTask(CreateUpdateTaskDto dto) {
        //костыль
        ProjectEntity project = projectService.getProjectEntityById(dto.getProjectId());
        UserEntity creator = userService.getUserEntityById(dto.getCreatorId());
        UserEntity editor = userService.getUserEntityById(dto.getEditorId());

        TaskEntity entity = TaskDtoConverter.convertDtoToEntity(dto, project, creator, editor);
        entity = taskRepository.save(entity);
        return TaskDtoConverter.convertEntityToDto(entity);
    }

    @Transactional(readOnly = true)
    public TaskDto getTaskDtoById(String id) {
        var entity = taskRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        return TaskDtoConverter.convertEntityToDto(entity);
    }

}
