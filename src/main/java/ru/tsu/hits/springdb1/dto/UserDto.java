package ru.tsu.hits.springdb1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tsu.hits.springdb1.entity.Role;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String id;
    private Date creationDate;
    private Date editDate;
    private String fullName;
    private String email;
    private String password;
    private Role role;
}
