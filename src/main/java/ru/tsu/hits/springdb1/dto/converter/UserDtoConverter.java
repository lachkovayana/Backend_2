package ru.tsu.hits.springdb1.dto.converter;

import ru.tsu.hits.springdb1.CsvClass;
import ru.tsu.hits.springdb1.dto.CreateUpdateUserDto;
import ru.tsu.hits.springdb1.dto.UserDto;
import ru.tsu.hits.springdb1.entity.UserEntity;
import ru.tsu.hits.springdb1.passwordMethods;

import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;

public class UserDtoConverter {

    public static UserEntity convertDtoToEntity(CreateUpdateUserDto dto) {
        UserEntity entity = new UserEntity();

        byte[] shaInBytes = passwordMethods.digest(dto.getPassword().getBytes(UTF_8), "SHA-256");

        entity.setUuid(UUID.randomUUID().toString());
        entity.setCreationDate(dto.getCreationDate());
        entity.setEditDate(dto.getEditDate());
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setPassword(passwordMethods.bytesToHex(shaInBytes));
        entity.setRole(dto.getRole());

        return entity;
    }

    public static UserDto convertEntityToDto(UserEntity entity) {
        UserDto dto = new UserDto();

        dto.setId(entity.getUuid());
        dto.setCreationDate(entity.getCreationDate());
        dto.setEditDate(entity.getEditDate());
        dto.setFullName(entity.getFullName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());

        return dto;
    }

    public static UserEntity convertCsvToEntity(CsvClass elem) {
        return new UserEntity(
                UUID.randomUUID().toString(),
                elem.getCreationDate(),
                elem.getEditDate(),
                elem.getFullName(),
                elem.getEmail(),
                elem.getPassword(),
                elem.getRole()
        );
    }

}
