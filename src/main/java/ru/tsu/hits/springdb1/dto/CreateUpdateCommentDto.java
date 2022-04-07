package ru.tsu.hits.springdb1.dto;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
public class CreateUpdateCommentDto {
    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByPosition(position = 0)
    private Date creationDate;

    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByPosition(position = 1)
    private Date editDate;

    @CsvBindByPosition(position = 2)
    private String authorId;

    @NonNull
    @CsvBindByPosition(position = 3)
    private String commentText;

    //-----------------
    @CsvBindByPosition(position = 4)
    private List<String> tasksId;  // few id of tasks
}
