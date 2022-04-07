package ru.tsu.hits.springdb1.exception;

public class TaskExceptionNotFound extends  RuntimeException{
    public TaskExceptionNotFound(String message) {
        super(message);
    }
}
