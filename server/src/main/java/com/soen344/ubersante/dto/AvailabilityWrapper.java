package com.soen344.ubersante.dto;

import com.soen344.ubersante.models.Patient;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

/**
 * Wrapper class for Availability from checkout
 * to backend
 */
public class AvailabilityWrapper {

    @NotEmpty
    private Patient patient;

    @NotEmpty
    private List<AvailabilityDetails> cart;

    public Patient getPatient() {
        return patient;
    }

    public List<AvailabilityDetails> getCart() {
        return cart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AvailabilityWrapper)) return false;
        AvailabilityWrapper that = (AvailabilityWrapper) o;
        return Objects.equals(getPatient(), that.getPatient()) &&
                Objects.equals(getCart(), that.getCart());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPatient(), getCart());
    }

    @Override
    public String toString() {
        return "AvailabilityWrapper{" +
                "patient=" + patient +
                ", cart=" + cart +
                '}';
    }
}
