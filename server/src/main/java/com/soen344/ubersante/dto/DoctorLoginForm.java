package com.soen344.ubersante.dto;

import com.soen344.ubersante.validation.ValidPermitNumber;

import javax.validation.constraints.NotEmpty;

public class DoctorLoginForm {

    @NotEmpty
    @ValidPermitNumber
    private String permitNumber;

    @NotEmpty
    private String password;

    public String getPermitNumber() {
        return permitNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPermitNumber(String healthCard) {
        this.permitNumber = permitNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DoctorLoginForm{" +
                "permitNumber='" + permitNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
