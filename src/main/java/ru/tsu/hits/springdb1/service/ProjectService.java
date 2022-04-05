package ru.tsu.hits.springdb1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsu.hits.springdb1.dto.CreateUpdateProjectDto;
import ru.tsu.hits.springdb1.dto.ProjectDto;
import ru.tsu.hits.springdb1.dto.converter.ProjectDtoConverter;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.exception.ProjectExceptionNotFound;
import ru.tsu.hits.springdb1.repository.ProjectRepository;
import ru.tsu.hits.springdb1.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Transactional
    public ProjectDto createProject(CreateUpdateProjectDto dto) {
        ProjectEntity projectEntity = ProjectDtoConverter.convertProjectDtoToEntity(dto);

        projectEntity = projectRepository.save(projectEntity);

        return ProjectDtoConverter.convertProjectEntityToDto(projectEntity, getTasksByProject(projectEntity));
    }

    @Transactional(readOnly = true)
    public ProjectDto getProjectDtoById(String id) {
        ProjectEntity projectEntity = getProjectEntityById(id);
        return ProjectDtoConverter.convertProjectEntityToDto(projectEntity, getTasksByProject(projectEntity));
    }

    @Transactional(readOnly = true)
    public ProjectEntity getProjectEntityById(String uuid) {
        return projectRepository.findById(uuid)
                .orElseThrow(() -> new ProjectExceptionNotFound("Проект с id " + uuid + " не найден"));
    }

    @Transactional(readOnly = true)
    public List<TaskEntity> getTasksByProject(ProjectEntity projectEntity) {
        return taskRepository.findByProject(projectEntity);
    }
}
