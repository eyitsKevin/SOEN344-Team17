package com.soen344.ubersante.users.models;

import com.soen344.ubersante.validation.ValidEmail;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "health_card")
    @NotEmpty
    private String healthCard;

    @Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    private String lastName;

    @Column(name = "birthday")
    @NotNull
    private String birthday;

    @Column(name = "gender")
    @NotEmpty
    @Pattern(regexp = "^Male$|^Female$|^Other$", message = "Gender must be either 'Male', 'Female', or 'Other'")
    private String gender;

    @Column(name = "phone")
    @NotEmpty
    private String phone;

    @Column(name = "email")
    @ValidEmail
    private String email;

    @Column(name = "address")
    @NotEmpty
    private String address;

    public Patient() {

    }

    public Patient(String healthCard, String firstName, String lastName, String birthday, String gender, String phone, String email, String address) {
        this.healthCard = healthCard;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Patient(Patient patient) {
        this.healthCard = patient.getHealthCard();
        this.firstName = patient.getFirstName();
        this.lastName = patient.getLastName();
        this.birthday = patient.getBirthday();
        this.gender = patient.getGender();
        this.phone = patient.getPhone();
        this.email = patient.getEmail();
        this.address = patient.getAddress();
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

    @Override
    public String toString() {
        return "Patient{" +
                "healthCard='" + healthCard + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return healthCard.equals(patient.healthCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(healthCard);
    }
}
