package com.arturfrimu.pattern.command.party.spring;

import java.util.Arrays;

public enum SLOT {
    LIGHT(0), TV(1);

    private final int value;

    SLOT(int i) {
        this.value = i;
    }

    public static SLOT getValue(String slot) {
        return SLOT.valueOf(slot);
    }

    public static SLOT getValue(int value) {
        return Arrays.stream(SLOT.values()).filter(s -> s.value == value).findFirst().orElse(null);
    }

    public static int getValue(SLOT slot) {
        return slot.value;
    }
}
