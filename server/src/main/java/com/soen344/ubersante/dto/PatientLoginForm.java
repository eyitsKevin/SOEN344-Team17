package com.soen344.ubersante.dto;

import com.soen344.ubersante.validation.ValidHealthCard;

import javax.validation.constraints.NotEmpty;

public class PatientLoginForm {

    @NotEmpty
    @ValidHealthCard
    private String healthCard;

    @NotEmpty
    private String password;

    public String getHealthCard() {
        return healthCard;
    }

    public String getPassword() {
        return password;
    }

    public void setHealthCard(String healthCard) {
        this.healthCard = healthCard;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PatientLoginForm{" +
                "healthCard='" + healthCard + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
