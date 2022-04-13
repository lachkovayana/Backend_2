package ru.tsu.hits.springdb1.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsu.hits.springdb1.dto.CommentDto;
import ru.tsu.hits.springdb1.dto.CreateUpdateCommentDto;
import ru.tsu.hits.springdb1.dto.CreateUpdateTaskDto;
import ru.tsu.hits.springdb1.dto.converter.CommentDtoConverter;
import ru.tsu.hits.springdb1.dto.converter.TaskDtoConverter;
import ru.tsu.hits.springdb1.entity.CommentEntity;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;
import ru.tsu.hits.springdb1.exception.CommentExceptionNotFound;
import ru.tsu.hits.springdb1.repository.CommentRepository;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    private final UserService userService;
    private final TaskService taskService;

    @Transactional
    public CommentDto createComment(CreateUpdateCommentDto dto) {
        var user = userService.getUserEntityById(dto.getAuthorId());

        var listOfTaskEntities = taskService.getTaskEntitiesByFewId(dto.getTasksId());

        var commentEntity = CommentDtoConverter.convertCommentDtoToEntity(dto, user, listOfTaskEntities);

        var savedEntity = commentRepository.save(commentEntity);
        return CommentDtoConverter.convertCommentEntityToDto(savedEntity);
    }

    @Transactional
    public void saveFromResource() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("comments.csv");
        var data = new CsvToBeanBuilder<CreateUpdateCommentDto>(new InputStreamReader(Objects.requireNonNull(inputStream)))
                .withSeparator(',')
                .withType(CreateUpdateCommentDto.class)
                .withSkipLines(1)
                .build()
                .parse();

        data.forEach(this::createComment);
    }

    @Transactional(readOnly = true)
    public CommentDto getCommentDtoById(String id) {
        var entity = commentRepository.findById(id)
                .orElseThrow(() -> new CommentExceptionNotFound("Комментарий с id " + id + " не найден"));
        return CommentDtoConverter.convertCommentEntityToDto(entity);
    }



}
