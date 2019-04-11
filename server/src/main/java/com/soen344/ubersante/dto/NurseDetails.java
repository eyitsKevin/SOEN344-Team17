package com.soen344.ubersante.dto;

import com.soen344.ubersante.models.Nurse;
import com.soen344.ubersante.validation.ValidAccessId;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class NurseDetails  {

    @ValidAccessId
    private String accessId;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    private long clinicId;

    public NurseDetails(Nurse nurse) {
        accessId = nurse.getAccessId();
        firstName = nurse.getFirstName();
        lastName = nurse.getLastName();
    }

    public String getAccessId() {
        return accessId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getClinicId() {
        return clinicId;
    }

    public void setClinicId(long clinicId) {
        this.clinicId = clinicId;
    }

    @Override
    public String toString() {
        return "NurseDetails{" +
                "accessId='" + accessId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NurseDetails)) return false;
        NurseDetails that = (NurseDetails) o;
        return accessId.equals(that.accessId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessId);
    }
}
