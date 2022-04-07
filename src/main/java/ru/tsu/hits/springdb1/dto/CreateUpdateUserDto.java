package ru.tsu.hits.springdb1.dto;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Data;
import ru.tsu.hits.springdb1.entity.Role;
import java.util.Date;

@Data
public class CreateUpdateUserDto {
    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByPosition(position = 0)
    private Date creationDate;
    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByPosition(position = 1)
    private Date editDate;
    @CsvBindByPosition(position = 2)
    private String fullName;
    @CsvBindByPosition(position = 3)
    private String email;
    @CsvBindByPosition(position = 4)
    private String password;
    @CsvBindByPosition(position = 5)
    private Role role;
}
