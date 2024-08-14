package com.flexicon.deck.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception thrown when a requested deck does not exist.
 *
 * @author isaac1000000
 */
@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class DeckDoesNotExistException extends RuntimeException {
    public DeckDoesNotExistException(String message) {
        super(message);
    }
}
