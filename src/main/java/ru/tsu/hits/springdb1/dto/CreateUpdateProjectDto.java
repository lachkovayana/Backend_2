package ru.tsu.hits.springdb1.dto;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class CreateUpdateProjectDto {
    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByPosition(position = 0)
    private Date creationDate;

    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByPosition(position = 1)
    private Date editDate;

    @NonNull
    @CsvBindByPosition(position = 2)
    private String name;

    @NonNull
    @CsvBindByPosition(position = 3)
    private String description;
}
