package ru.tsu.hits.springdb1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tsu.hits.springdb1.entity.CommentEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, String> {
    List<CommentEntity> findByAuthor(UserEntity userEntity);
    List<CommentEntity> findByTasks(TaskEntity taskEntity);
    List<CommentEntity> findAllByCommentTextLike(String text);
}
