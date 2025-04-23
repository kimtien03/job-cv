package com.example.BEJobApplication.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoFoundException extends RuntimeException {
    public NoFoundException(String message) {
        super(message);
    }
}
