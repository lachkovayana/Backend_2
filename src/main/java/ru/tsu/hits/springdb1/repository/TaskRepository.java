package ru.tsu.hits.springdb1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tsu.hits.springdb1.entity.CommentEntity;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, String> {
    List<TaskEntity> findByProject(ProjectEntity projectEntity);

    List<TaskEntity> findByCreator(UserEntity userEntity);

    List<TaskEntity> findByEditor(UserEntity userEntity);

    List<TaskEntity> findByComments(CommentEntity commentEntity);

    List<TaskEntity> findAllByComments(CommentEntity commentEntity);

}
