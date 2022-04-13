package ru.tsu.hits.springdb1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tsu.hits.springdb1.dto.CreateUpdateUserDto;
import ru.tsu.hits.springdb1.dto.FetchUserDto;
import ru.tsu.hits.springdb1.dto.UserDto;
import ru.tsu.hits.springdb1.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserDto save(@Valid @RequestBody CreateUpdateUserDto createUpdateUserDto) {
        return userService.save(createUpdateUserDto);
    }


    @GetMapping("/{id}")
    public UserDto getById(@PathVariable UUID id) {
        return userService.getUserDtoById(id.toString());
    }

    @GetMapping("/fetch")
    public  List<UserDto> getByAll(@RequestBody FetchUserDto dto){
        return userService.fetchUsers(dto);
    }
}
