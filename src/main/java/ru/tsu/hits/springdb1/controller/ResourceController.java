package ru.tsu.hits.springdb1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tsu.hits.springdb1.service.CommentService;
import ru.tsu.hits.springdb1.service.ProjectService;
import ru.tsu.hits.springdb1.service.TaskService;
import ru.tsu.hits.springdb1.service.UserService;

@RestController
@RequestMapping("/resources")
@RequiredArgsConstructor
public class ResourceController {

    private  final  UserService userService;
    private  final ProjectService projectService;
    private  final TaskService taskService;
    private  final CommentService commentService;
    @PostMapping
    public void saveFromResource() {
//        userService.saveFromResource();
//        projectService.saveFromResource();
//        taskService.saveFromResource();
        commentService.saveFromResource();
    }

}
