package com.soen344.ubersante.dto;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

/**
 * Wrapper class for Availability from checkout
 * to backend
 */
public class UpdateAppointmentWrapper {

    @NotEmpty
    private PatientDetails patient;

    @NotEmpty
    private List<AvailabilityDetails> cart;

    @NotEmpty
    private long appointmentId;

    public PatientDetails getPatient() {
        return patient;
    }

    public List<AvailabilityDetails> getCart() {
        return cart;
    }

    public long getAppointmentId(){
        return appointmentId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AvailabilityWrapper)) return false;
        UpdateAppointmentWrapper that = (UpdateAppointmentWrapper) o;
        return Objects.equals(getPatient(), that.getPatient()) &&
                Objects.equals(getCart(), that.getCart()) &&
                Objects.equals(getAppointmentId(), that.getAppointmentId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPatient(), getCart(), getAppointmentId());
    }

    @Override
    public String toString() {
        return "AvailabilityWrapper{" +
                "patient=" + patient +
                ", cart=" + cart +
                ", appointmentId=" + appointmentId +
                '}';
    }
}
