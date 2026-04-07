package com.streaksmart.model;

public enum Frequency {
    DAILY("Daily");

    private final String displayName;

    Frequency(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
