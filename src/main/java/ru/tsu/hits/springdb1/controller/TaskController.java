package ru.tsu.hits.springdb1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tsu.hits.springdb1.dto.CreateUpdateTaskDto;
import ru.tsu.hits.springdb1.dto.TaskDto;
import ru.tsu.hits.springdb1.service.TaskService;

import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public TaskDto save(@RequestBody CreateUpdateTaskDto createUpdateTaskDto) {
        return taskService.createTask(createUpdateTaskDto);
    }

    @GetMapping("/{id}")
    public TaskDto getById(@PathVariable UUID id) {
        return taskService.getTaskDtoById(id.toString());
    }

}
