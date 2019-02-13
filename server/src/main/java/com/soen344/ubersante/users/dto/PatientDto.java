package com.soen344.ubersante.users.dto;

import com.soen344.ubersante.validation.PasswordMatches;
import com.soen344.ubersante.validation.ValidEmail;
import com.soen344.ubersante.validation.ValidHealthCard;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@PasswordMatches
public class PatientDto {

    @NotEmpty
    @ValidHealthCard
    private String healthCard;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotNull
    private String birthday;

    @NotEmpty
    private String gender;

    @NotEmpty
    private String phone;

    @ValidEmail
    private String email;

    @NotEmpty
    private String address;

    @NotEmpty
    private String password;

    @NotEmpty
    private String matchingPassword;

    public String getHealthCard() {
        return healthCard;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setHealthCard(String healthCard) {
        this.healthCard = healthCard;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
