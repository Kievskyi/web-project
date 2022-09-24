package com.example.webproject.models;

public enum Step {

    FIRST(1),
    SECOND(2),
    THIRD(3);

    private final int step;

    Step(int step) {
        this.step = step;
    }

    public int returnNumericStep() {
        return step;
    }

    public Step next() {
        if (ordinal() == values().length - 1) {
            return values()[values().length - 1];
        }
        return values()[ordinal() + 1];
    }
}