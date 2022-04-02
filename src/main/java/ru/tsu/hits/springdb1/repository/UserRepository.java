package ru.tsu.hits.springdb1.repository;

import org.springframework.data.repository.CrudRepository;
import ru.tsu.hits.springdb1.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}
