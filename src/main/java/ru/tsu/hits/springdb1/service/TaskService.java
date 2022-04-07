package ru.tsu.hits.springdb1.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsu.hits.springdb1.dto.CreateUpdateProjectDto;
import ru.tsu.hits.springdb1.dto.CreateUpdateTaskDto;
import ru.tsu.hits.springdb1.dto.TaskDto;
import ru.tsu.hits.springdb1.dto.converter.ProjectDtoConverter;
import ru.tsu.hits.springdb1.dto.converter.TaskDtoConverter;
import ru.tsu.hits.springdb1.entity.CommentEntity;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;
import ru.tsu.hits.springdb1.exception.TaskExceptionNotFound;
import ru.tsu.hits.springdb1.repository.CommentRepository;
import ru.tsu.hits.springdb1.repository.TaskRepository;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;

    private final ProjectService projectService;
    private final UserService userService;

    @Transactional
    public TaskDto createTask(CreateUpdateTaskDto dto) {
        // ???
        ProjectEntity project = projectService.getProjectEntityById(dto.getProjectId());
        UserEntity creator = userService.getUserEntityById(dto.getCreatorId());
        UserEntity editor = userService.getUserEntityById(dto.getEditorId());


        TaskEntity entity = TaskDtoConverter.convertDtoToEntity(dto, project, creator, editor);
        entity = taskRepository.save(entity);
        return TaskDtoConverter.convertEntityToDto(entity, getCommentsByTask(entity));
    }
    @Transactional
    public void saveFromResource() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("tasks.csv");
        var data = new CsvToBeanBuilder<CreateUpdateTaskDto>(new InputStreamReader(Objects.requireNonNull(inputStream)))
                .withSeparator(',')
                .withType(CreateUpdateTaskDto.class)
                .withSkipLines(1)
                .build()
                .parse();

        data.forEach((elem) -> {
            ProjectEntity project = projectService.getProjectEntityById(elem.getProjectId());
            UserEntity creator = userService.getUserEntityById(elem.getCreatorId());
            UserEntity editor = userService.getUserEntityById(elem.getEditorId());

            var entity = TaskDtoConverter.convertDtoToEntity(elem, project, creator, editor);
            var savedEntity = taskRepository.save(entity);
        });
    }
    @Transactional(readOnly = true)
    public TaskDto getTaskDtoById(String id) {
        var entity = getTaskEntityById(id);
        return TaskDtoConverter.convertEntityToDto(entity, getCommentsByTask(entity));
    }

    @Transactional(readOnly = true)
    public TaskEntity getTaskEntityById(String id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskExceptionNotFound("Задача с id " + id + " не найдена"));
    }

    @Transactional(readOnly = true)
    public List<TaskEntity> getTaskEntitiesByFewId(List<String> listOfId) {
        List<TaskEntity> result = new ArrayList<>();
//        String[] idList;
//        idList = fewId.split(",");
//        Arrays.stream(idList).forEach(id ->{
//            result.add(getTaskEntityById(id));
//        });
        listOfId.forEach(id -> result.add(getTaskEntityById(id)));
        return result;
    }


    @Transactional(readOnly = true)
    public List<CommentEntity> getCommentsByTask(TaskEntity taskEntity) {
        return commentRepository.findByTasks(taskEntity);
    }

}
