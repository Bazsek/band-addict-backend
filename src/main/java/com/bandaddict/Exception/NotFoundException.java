package com.bandaddict.Exception;

/**
 * Not found exception
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(final String message){
        super(message);
    }
}
