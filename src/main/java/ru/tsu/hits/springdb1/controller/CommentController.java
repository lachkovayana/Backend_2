package ru.tsu.hits.springdb1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tsu.hits.springdb1.dto.CommentDto;
import ru.tsu.hits.springdb1.dto.CreateUpdateCommentDto;
import ru.tsu.hits.springdb1.service.CommentService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public CommentDto save(@Valid  @RequestBody CreateUpdateCommentDto createUpdateCommentDto) {
        return commentService.createComment(createUpdateCommentDto);
    }

    @GetMapping("/{id}")
    public CommentDto getById(@PathVariable UUID id) {
        return commentService.getCommentDtoById(id.toString());
    }

}
