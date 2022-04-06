package ru.tsu.hits.springdb1.exception;

public class CommentExceptionNotFound extends RuntimeException {
    public CommentExceptionNotFound(String message) {
        super(message);
    }
}
