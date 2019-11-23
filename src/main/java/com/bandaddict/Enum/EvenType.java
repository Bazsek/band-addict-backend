package com.bandaddict.Enum;

public enum EvenType {
    DELETED("Deleted"),

    ONE_DAY("Short"),

    LONG("Long");

    private String value;

    EvenType(final String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    public static EvenType getEnum(final String value) {
        for(EvenType event: EvenType.values()) {
            if(event.value.equals(value)) {
                return event;
            }
        }
        return null;
    }
}
