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
import ru.tsu.hits.springdb1.entity.UserEntity;
import ru.tsu.hits.springdb1.repository.UserRepository;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public static byte[] digest(byte[] input, String algorithm) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] result = md.digest(input);
        return result;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @Transactional
    public UserDto save(CreateUpdateUserDto createUpdateUserDto) {
        byte[] shaInBytes = digest(createUpdateUserDto.getPassword().getBytes(UTF_8), "SHA-256");

        var entity = new UserEntity(
                UUID.randomUUID().toString(),
                createUpdateUserDto.getCreationDate(),
                createUpdateUserDto.getEditDate(),
                createUpdateUserDto.getFullName(),
                createUpdateUserDto.getEmail(),
                bytesToHex(shaInBytes),
                createUpdateUserDto.getRole()
        );
        var savedEntity = userRepository.save(entity);
        return new UserDto(
                savedEntity.getUuid(),
                savedEntity.getCreationDate(),
                savedEntity.getEditDate(),
                savedEntity.getFullName(),
                savedEntity.getEmail(),
                savedEntity.getPassword(),
                savedEntity.getRole()
        );
    }

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
            var entity = new UserEntity(
                    UUID.randomUUID().toString(),
                    elem.getCreationDate(),
                    elem.getEditDate(),
                    elem.getFullName(),
                    elem.getEmail(),
                    elem.getPassword(),
                    elem.getRole()
            );
            var savedEntity = userRepository.save(entity);
            result.add(new UserDto(
                    savedEntity.getUuid(),
                    savedEntity.getCreationDate(),
                    savedEntity.getEditDate(),
                    savedEntity.getFullName(),
                    savedEntity.getEmail(),
                    savedEntity.getPassword(),
                    savedEntity.getRole()
            ));
        });
        return result;

    }

    public UserDto getById(String id) {
        var entity = userRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        return new UserDto(
                entity.getUuid(),
                entity.getCreationDate(),
                entity.getEditDate(),
                entity.getFullName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRole()

        );
    }
}
