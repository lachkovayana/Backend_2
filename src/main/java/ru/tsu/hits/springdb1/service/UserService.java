package ru.tsu.hits.springdb1.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsu.hits.springdb1.CsvClass;
import ru.tsu.hits.springdb1.dto.CreateUpdateUserDto;
import ru.tsu.hits.springdb1.dto.UserDto;
import ru.tsu.hits.springdb1.dto.converter.UserDtoConverter;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;
import ru.tsu.hits.springdb1.exception.UserExceptionNotFound;
import ru.tsu.hits.springdb1.repository.TaskRepository;
import ru.tsu.hits.springdb1.repository.UserRepository;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Transactional
    public UserDto save(CreateUpdateUserDto createUpdateUserDto) {
        var userEntity = UserDtoConverter.convertDtoToEntity(createUpdateUserDto);
        var savedEntity = userRepository.save(userEntity);
        return UserDtoConverter.convertEntityToDto(savedEntity, getCreatedTasksByUser(savedEntity), getEditedTasksByUser(savedEntity));
    }

    @Transactional
    public List<UserDto> saveFromResource() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("text.csv");
        var users = new CsvToBeanBuilder<CsvClass>(new InputStreamReader(Objects.requireNonNull(inputStream))).withSeparator(',').withType(CsvClass.class).withSkipLines(1).build().parse();

        List<UserDto> result = new ArrayList<>();
        users.forEach((elem) -> {
            var entity = UserDtoConverter.convertCsvToEntity(elem);
            var savedEntity = userRepository.save(entity);
            result.add(UserDtoConverter.convertEntityToDto(savedEntity, getCreatedTasksByUser(savedEntity), getEditedTasksByUser(savedEntity)));
        });
        return result;
    }

    @Transactional(readOnly = true)
    public UserDto getUserDtoById(String id) {
        UserEntity entity = getUserEntityById(id);
        return UserDtoConverter.convertEntityToDto(entity, getCreatedTasksByUser(entity), getEditedTasksByUser(entity));
    }

    @Transactional(readOnly = true)
    public UserEntity getUserEntityById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new UserExceptionNotFound("Пользователь с id " + id + " не найден"));
    }

    @Transactional(readOnly = true)
    public List<TaskEntity> getCreatedTasksByUser(UserEntity userEntity) {
        return taskRepository.findByCreator(userEntity);
    }

    @Transactional(readOnly = true)
    public List<TaskEntity> getEditedTasksByUser(UserEntity userEntity) {
        return taskRepository.findByEditor(userEntity);
    }
}
