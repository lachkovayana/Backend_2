package ru.tsu.hits.springdb1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import ru.tsu.hits.springdb1.dto.CommentDto;
import ru.tsu.hits.springdb1.dto.CreateUpdateCommentDto;
import ru.tsu.hits.springdb1.dto.TaskDto;
import ru.tsu.hits.springdb1.dto.converter.CommentDtoConverter;
import ru.tsu.hits.springdb1.entity.CommentEntity;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;
import ru.tsu.hits.springdb1.exception.CommentExceptionNotFound;
import ru.tsu.hits.springdb1.repository.CommentRepository;
import ru.tsu.hits.springdb1.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    private final UserService userService;

    @Transactional
    public CommentDto createComment(CreateUpdateCommentDto dto) {
        var user = userService.getUserEntityById(dto.getAuthorId());
        var entity = CommentDtoConverter.convertCommentDtoToEntity(dto, user);
        var savedEntity = commentRepository.save(entity);
        return CommentDtoConverter.convertCommentEntityToDto(savedEntity, getTasksByComment(entity));
    }

    @Transactional(readOnly = true)
    public CommentDto getCommentDtoById(String id) {
        var entity = commentRepository.findById(id)
                .orElseThrow(() -> new CommentExceptionNotFound("Комментарий с id " + id + " не найден"));
        return CommentDtoConverter.convertCommentEntityToDto(entity, getTasksByComment(entity));
    }

    @Transactional(readOnly = true)
    public List<TaskEntity> getTasksByComment(CommentEntity commentEntity) {
        return taskRepository.findByComments(commentEntity);
    }
}
