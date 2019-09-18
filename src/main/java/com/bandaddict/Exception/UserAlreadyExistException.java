package com.bandaddict.Exception;

/**
 * the user already exist exception
 */
public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(final String message){
        super(message);
    }
}
