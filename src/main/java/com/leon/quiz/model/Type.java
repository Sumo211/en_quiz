package com.leon.quiz.model;

public enum Type {

    RADIOGROUP("R"), CHECKBOX("C");

    private String shortName;

    Type(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public static Type fromShortName(String shortName) {
        switch (shortName) {
            case "R":
                return Type.RADIOGROUP;
            case "C":
                return Type.CHECKBOX;
            default:
                throw new IllegalArgumentException("ShortName [" + shortName + "] not supported.");
        }
    }

}
