package com.soen344.ubersante.dto;

import com.soen344.ubersante.enums.AppointmentType;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * dto class to convert {@link AvailabilityDetails}
 * to {@link com.soen344.ubersante.models.Availability}
 */
public class AvailabilityDetails {

    @NotEmpty
    private String doctorPermitNumber;

    @NotEmpty
    private AppointmentType appointmentType;

    @NotEmpty
    private String startTime;

    @NotEmpty
    private String endTime;

    public String getDoctorPermitNumber() {
        return doctorPermitNumber;
    }

    public void setDoctorPermitNumber(String doctorPermitNumber) {
        this.doctorPermitNumber = doctorPermitNumber;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AvailabilityDetails)) return false;
        AvailabilityDetails that = (AvailabilityDetails) o;
        return Objects.equals(getDoctorPermitNumber(), that.getDoctorPermitNumber()) &&
                getAppointmentType() == that.getAppointmentType() &&
                Objects.equals(getStartTime(), that.getStartTime()) &&
                Objects.equals(getEndTime(), that.getEndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDoctorPermitNumber(), getAppointmentType(), getStartTime(), getEndTime());
    }

    @Override
    public String toString() {
        return "AvailabilityDetails{" +
                "doctorPermitNumber='" + doctorPermitNumber + '\'' +
                ", appointmentType=" + appointmentType +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
