package com.flexicon.deck.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class DeckDoesNotExistException extends RuntimeException {
    public DeckDoesNotExistException(String message) {
        super(message);
    }
}
