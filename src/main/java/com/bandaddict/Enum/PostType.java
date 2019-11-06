package com.bandaddict.Enum;

public enum PostType {

    ADVERTISEMENT("Advertisement"),

    EVENT("Event"),

    CURIOSITY("Curiosity"),

    OTHER("Other");

    private String value;

    PostType(final String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    public static PostType getEnum(final String value) {
        for(PostType postType: PostType.values()) {
            if(postType.value.equals(value)) {
                return postType;
            }
        }
        return null;
    }
}
