package com.soen344.ubersante.dto;

import com.soen344.ubersante.enums.AppointmentType;
import com.soen344.ubersante.models.Clinic;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * dto class to convert {@link AvailabilityDetails}
 * to {@link com.soen344.ubersante.models.Availability}
 */
public class AvailabilityDetails {

    @NotEmpty
    private long id;

    @NotEmpty
    private String doctorPermitNumber;

    @NotEmpty
    private AppointmentType appointmentType;

    @NotEmpty
    private String startTime;

    @NotEmpty
    private String endTime;

    @NotEmpty
    private Clinic clinic;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AvailabilityDetails)) return false;
        AvailabilityDetails that = (AvailabilityDetails) o;
        return getId() == that.getId() &&
                Objects.equals(getDoctorPermitNumber(), that.getDoctorPermitNumber()) &&
                getAppointmentType() == that.getAppointmentType() &&
                Objects.equals(getStartTime(), that.getStartTime()) &&
                Objects.equals(getEndTime(), that.getEndTime()) &&
                Objects.equals(getClinic(), that.getClinic());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDoctorPermitNumber(), getAppointmentType(), getStartTime(), getEndTime(), getClinic());
    }
}
