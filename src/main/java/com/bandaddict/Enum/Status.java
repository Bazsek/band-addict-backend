package com.bandaddict.Enum;

/**
 * the Status enum
 */
public enum Status {

    ACTIVE(0),

    INACTIVE(1),

    BANNED(2);

    private int value;

    Status(final int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
