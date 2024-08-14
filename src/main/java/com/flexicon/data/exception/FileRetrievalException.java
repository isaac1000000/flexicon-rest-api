package com.flexicon.data.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception thrown when a file could not be found or is otherwise unusable.
 *
 * @author isaac1000000
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileRetrievalException extends RuntimeException{
    public FileRetrievalException(String message) {
        super(message);
    }
}
