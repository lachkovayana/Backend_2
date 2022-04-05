package ru.tsu.hits.springdb1.dto;

import lombok.Data;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;

import java.util.Date;

@Data
public class CreateUpdateTaskDto {
    private Date creationDate;
    private Date editDate;
    private String title;
    private String description;
    private String creatorId;
    private String editorId;
    private String priority;
    private int timeEstimate;
    private String projectId;
}
