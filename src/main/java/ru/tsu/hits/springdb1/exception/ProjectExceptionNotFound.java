package ru.tsu.hits.springdb1.exception;

public class ProjectExceptionNotFound extends  RuntimeException{
    public ProjectExceptionNotFound(String message) {
        super(message);
    }
}
