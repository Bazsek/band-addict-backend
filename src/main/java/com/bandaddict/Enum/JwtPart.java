package com.bandaddict.Enum;

/**
 * Jwt attribute enum
 */
public enum JwtPart {

    HEADER("Authorization"),

    PREFIX("Bearer ");

    private String value;

    JwtPart(final String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
