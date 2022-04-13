package ru.tsu.hits.springdb1.dto;

import lombok.Data;
import ru.tsu.hits.springdb1.entity.Role;

import java.util.Date;

@Data
public class SearchUserByAllDto {
    private Date creationDate;
    private Date editDate;
    private String fullName;
    private String email;
    private String password;
    private Role role;
}
