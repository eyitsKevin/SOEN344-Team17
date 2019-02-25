package com.soen344.ubersante.enums;

public enum AppointmentType {

    WALK_IN (20),
    ANNUAL_CHECKUP (60);

    private final int duration;

    AppointmentType(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return this.duration;
    }

}