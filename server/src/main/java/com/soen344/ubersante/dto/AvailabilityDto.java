package com.soen344.ubersante.dto;

import com.soen344.ubersante.enums.AppointmentType;
import com.soen344.ubersante.validation.ValidPermitNumber;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.Objects;

public class AvailabilityDto {

    private long id;

    @ValidPermitNumber
    private String doctorPermitNumber;

    @Pattern(regexp = "^checkup$|^walkin$", message = "Title must be checkup OR walkin")
    private String title;

    private String start;

    @Positive
    private int duration;

    private String end;

    public AvailabilityDto() { }

    public long getId() {
        return id;
    }

    public String getDoctorPermitNumber() {
        return doctorPermitNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getStart() {
        return start;
    }

    public int getDuration() {
        return duration;
    }

    public String getEnd() {
        return end;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDoctorPermitNumber(String doctorPermitNumber) {
        this.doctorPermitNumber = doctorPermitNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitle(AppointmentType type) {
        if (type == AppointmentType.WALK_IN) {
            this.title = "walkin";
        } else if (type == AppointmentType.ANNUAL_CHECKUP) {
            this.title = "checkup";
        }
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "AvailabilityDto{" +
                "id=" + id +
                ", doctorPermitNumber='" + doctorPermitNumber + '\'' +
                ", title='" + title + '\'' +
                ", start='" + start + '\'' +
                ", duration=" + duration +
                ", end='" + end + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AvailabilityDto)) return false;
        AvailabilityDto that = (AvailabilityDto) o;
        return id == that.id &&
                duration == that.duration &&
                Objects.equals(doctorPermitNumber, that.doctorPermitNumber) &&
                Objects.equals(title, that.title) &&
                Objects.equals(start, that.start) &&
                Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctorPermitNumber, title, start, duration, end);
    }
}
