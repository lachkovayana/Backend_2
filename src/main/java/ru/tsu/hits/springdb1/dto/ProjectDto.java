package ru.tsu.hits.springdb1.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectDto {
    private String id;
    private Date creationDate;
    private Date editDate;
    private String name;
    private String description;
}
