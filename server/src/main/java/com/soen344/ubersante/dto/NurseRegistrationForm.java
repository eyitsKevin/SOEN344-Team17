package com.soen344.ubersante.dto;

import com.soen344.ubersante.validation.ValidAccessId;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class NurseRegistrationForm {

    @ValidAccessId
    private String accessId;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String password;

    @Positive
    private long clinicId;

    public NurseRegistrationForm() {
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getClinicId() {
        return clinicId;
    }

    public void setClinicId(long clinicId) {
        this.clinicId = clinicId;
    }
}
