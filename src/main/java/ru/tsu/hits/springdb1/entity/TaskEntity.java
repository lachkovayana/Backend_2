package ru.tsu.hits.springdb1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    @Id
    @Column(name = "id")
    private String uuid;

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date creationDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "edit_date")
    private Date editDate;

    @NotEmpty(message = "Title is mandatory")
    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "creator", referencedColumnName = "id")
    private UserEntity creator;

    @ManyToOne
    @JoinColumn(name = "editor", referencedColumnName = "id")
    private UserEntity editor;

    private String priority;

    @ManyToOne
    @JoinColumn(name = "project", referencedColumnName = "id")
    private ProjectEntity project;

    @Column(name = "time_estimate")
    private int timeEstimate;
}
