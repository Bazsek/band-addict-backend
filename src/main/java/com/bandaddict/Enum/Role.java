package com.bandaddict.Enum;

/**
 * the User Role enum
 */
public enum Role {

    ADMIN("ADMIN"),

    USER("USER"),

    MEMBER( "MEMBER"),

    FRONTMAN("FRONTMAN"),

    GUITARIST("GUITARIST"),

    BASS("BASS"),

    VOCAL("VOCAL"),

    KEYBOARD("KEYBOARD"),

    DJ("DJ"),

    DRUMMER("DRUMMER"),

    OTHER("OTHER");

    private String value;

    Role(final String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    public static Role getEnum(final String value) {
        for(Role role: Role.values()) {
            if(role.value.equals(value)) {
                return role;
            }
        }
        return null;
    }
}
