package ru.tsu.hits.springdb1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsu.hits.springdb1.dto.CreateUpdateProjectDto;
import ru.tsu.hits.springdb1.dto.ProjectDto;
import ru.tsu.hits.springdb1.dto.converter.ProjectDtoConverter;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.exception.ProjectExceptionNotFound;
import ru.tsu.hits.springdb1.repository.ProjectRepository;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Transactional
    public ProjectDto save(CreateUpdateProjectDto dto) {
        ProjectEntity projectEntity = ProjectDtoConverter.convertDtoToEntity(dto);

        projectEntity = projectRepository.save(projectEntity);

        return ProjectDtoConverter.convertEntityToDto(projectEntity);
    }

    @Transactional
    public ProjectDto getById(String id){
        ProjectEntity projectEntity = getProjectEntityById(id);
        return ProjectDtoConverter.convertEntityToDto(projectEntity);
    }

    @Transactional
    public ProjectEntity getProjectEntityById(String uuid){
        return  projectRepository.findById(uuid)
                .orElseThrow(()-> new ProjectExceptionNotFound("Проект с id " + uuid + " не найден"));
    }
}
