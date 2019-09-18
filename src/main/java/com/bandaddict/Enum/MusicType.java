package com.bandaddict.Enum;

public enum MusicType {

    OWN("Own"),

    COVER("Cover"),

    PARODY("Parody");

    private String value;

    MusicType(final String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    public static MusicType getEnum(final String value) {
        for(MusicType musicType: MusicType.values()) {
            if(musicType.value.equals(value)) {
                return musicType;
            }
        }
        return null;
    }
}
