package ru.tsu.hits.springdb1.dto;

import lombok.Data;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;

import java.util.Date;

@Data
public class TaskDto {
    private String id;
    private Date creationDate;
    private Date editDate;
    private String title;
    private String description;
    private UserEntity creator;
    private UserEntity editor;
    private String priority;
    private int timeEstimate;
    private ProjectEntity project;
}
