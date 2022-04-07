package ru.tsu.hits.springdb1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tsu.hits.springdb1.service.ProjectService;
import ru.tsu.hits.springdb1.service.UserService;

@RestController
@RequestMapping("/resources")
@RequiredArgsConstructor
public class ResourceController {

    private  final  UserService userService;
    private  final ProjectService projectService;
    @PostMapping
    public void saveFromResource() {
        userService.saveFromResource();
        projectService.saveFromResource();
    }

}
