package com.soen344.ubersante.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class ClinicRegistrationForm {

    private final String TIME_FORMAT = "^\\d\\d:\\d\\d:\\d\\d$";

    @NotBlank
    private String name;

    @Positive
    private int rooms;

    @Pattern(regexp = TIME_FORMAT)
    private String openTime;

    @Pattern(regexp = TIME_FORMAT)
    private String closeTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }
}
