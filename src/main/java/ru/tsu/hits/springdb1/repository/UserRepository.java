package ru.tsu.hits.springdb1.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import ru.tsu.hits.springdb1.entity.UserEntity;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {
    @Override
    List<UserEntity> findAll(Specification<UserEntity> spec);
}
