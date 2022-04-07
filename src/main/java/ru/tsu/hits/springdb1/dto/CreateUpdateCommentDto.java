package ru.tsu.hits.springdb1.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreateUpdateCommentDto {
    private Date creationDate;
    private Date editDate;
    private String authorId;
    private String commentText;

    //-----------------
    private List<String> tasksId;  // few id of tasks
}
