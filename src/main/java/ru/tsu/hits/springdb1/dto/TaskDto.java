package ru.tsu.hits.springdb1.dto;

import lombok.Data;
import ru.tsu.hits.springdb1.entity.ProjectEntity;

import java.util.Date;
import java.util.List;

@Data
public class TaskDto {
    private String id;
    private Date creationDate;
    private Date editDate;
    private String title;
    private String description;
    private String creator;
    private String editor;
    private String priority;
    private int timeEstimate;
    private String project;

    private List<CommentDto> comments;
}
