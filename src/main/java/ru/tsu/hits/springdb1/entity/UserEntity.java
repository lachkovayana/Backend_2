package ru.tsu.hits.springdb1.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @Column(name="id")
    private String uuid;

    @Temporal(TemporalType.DATE)
    @Column(name="creation_date")
    private Date creationDate;

    @Temporal(TemporalType.DATE)
    @Column(name="edit_date")
    private Date editDate;

    @Column (name="full_name")
    private String fullName;

    @Column
    private String email;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;
}
