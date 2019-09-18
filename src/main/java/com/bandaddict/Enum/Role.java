package com.bandaddict.Enum;

/**
 * the User Role enum
 */
public enum Role {

    ADMIN("ADMIN"),

    USER("USER"),

    MEMBER( "MEMBER"),

    FRONTMAN("FRONTMAN");

    private String value;

    Role(final String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
