package com.bandaddict.Exception;

public class PrefillDataException extends RuntimeException {
    public PrefillDataException(final String message, final Throwable exception){
        super(message);
    }
}
