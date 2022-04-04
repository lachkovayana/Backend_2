package ru.tsu.hits.springdb1.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import ru.tsu.hits.springdb1.CsvClass;
import ru.tsu.hits.springdb1.dto.CreateUpdateUserDto;
import ru.tsu.hits.springdb1.dto.UserDto;
import ru.tsu.hits.springdb1.dto.converter.UserDtoConverter;
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

    @Transactional
    public UserDto save(CreateUpdateUserDto createUpdateUserDto) {

        var userEntity = UserDtoConverter.convertDtoToEntity(createUpdateUserDto);
        var savedEntity = userRepository.save(userEntity);
        return UserDtoConverter.convertEntityToDto(savedEntity);
    }

    @Transactional
    public List<UserDto> saveFromResource() {
        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream("text.csv");
        var users = new CsvToBeanBuilder<CsvClass>(new InputStreamReader(Objects.requireNonNull(inputStream)))
                .withSeparator(',')
                .withType(CsvClass.class)
                .withSkipLines(1)
                .build()
                .parse();

        List<UserDto> result = new ArrayList<>();
        users.forEach((elem) -> {
            var entity = UserDtoConverter.convertCsvToEntity(elem);
            var savedEntity = userRepository.save(entity);
            result.add(UserDtoConverter.convertEntityToDto(savedEntity));
        });
        return result;

    }

    @Transactional
    public UserDto getById(String id) {
        var entity = userRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        return UserDtoConverter.convertEntityToDto(entity);
    }
}
