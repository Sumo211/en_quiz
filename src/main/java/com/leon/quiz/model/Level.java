package com.leon.quiz.model;

public enum Level {

    EASY("E"), MEDIUM("M"), ADVANCE("A");

    private String shortName;

    Level(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public static Level fromShortName(String shortName) {
        switch (shortName) {
            case "E":
                return Level.EASY;
            case "M":
                return Level.MEDIUM;
            case "A":
                return Level.ADVANCE;
            default:
                throw new IllegalArgumentException("ShortName [" + shortName + "] not supported.");
        }
    }

}
