package ru.tsu.hits.springdb1.service;

import com.opencsv.bean.CsvToBeanBuilder;
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

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

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
    public ProjectEntity getProjectEntityById(String id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectExceptionNotFound("Проект с id " + id + " не найден"));
    }

    @Transactional(readOnly = true)
    public List<TaskEntity> getTasksByProject(ProjectEntity projectEntity) {
        return taskRepository.findByProject(projectEntity);
    }

    @Transactional
    public void saveFromResource() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("projects.csv");
        var data = new CsvToBeanBuilder<CreateUpdateProjectDto>(new InputStreamReader(Objects.requireNonNull(inputStream)))
                .withSeparator(',')
                .withType(CreateUpdateProjectDto.class)
                .withSkipLines(1)
                .build()
                .parse();

        data.forEach((elem) -> {
            var entity = ProjectDtoConverter.convertProjectDtoToEntity(elem);
            var savedEntity = projectRepository.save(entity);
        });
    }

}
