package ru.tsu.hits.springdb1.exception;

public class UserExceptionNotFound extends  RuntimeException{
    public UserExceptionNotFound(String message) {
        super(message);
    }
}
