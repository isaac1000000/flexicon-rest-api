package com.flexicon.data.exception;

/**
 * An exception thrown when a DTO object could not be traced back to an original entry in a repository.
 *
 * @author isaac1000000
 */
public class ReassignmentMappingFailureException extends Exception{
    public ReassignmentMappingFailureException(String errorMessage) {
        super(errorMessage);
    }
}
