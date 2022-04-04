package ru.tsu.hits.springdb1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tsu.hits.springdb1.dto.CreateUpdateProjectDto;
import ru.tsu.hits.springdb1.dto.ProjectDto;
import ru.tsu.hits.springdb1.service.ProjectService;
import java.util.UUID;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ProjectDto save(@RequestBody CreateUpdateProjectDto createUpdateProjectDto) {
        return projectService.save(createUpdateProjectDto);
    }

    @GetMapping("/{id}")
    public ProjectDto getById(@PathVariable UUID id) {
        return projectService.getById(id.toString());
    }
}
