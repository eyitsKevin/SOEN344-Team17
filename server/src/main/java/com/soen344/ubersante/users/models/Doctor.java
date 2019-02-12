package com.soen344.ubersante.users.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Id
    @Column(name = "permit_number")
    private String permitNumber;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "city")
    private String city;

    @Column(name = "password")
    private String password;

    public Doctor() {

    }

    public Doctor(String permitNumber, String lastName, String firstName, String specialty, String city, String password) {
        this.permitNumber = permitNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.specialty = specialty;
        this.city = city;
        this.password = password;
    }

    public Doctor(Doctor doctor) {
        this.permitNumber = doctor.getPermitNumber();
        this.lastName = doctor.getLastName();
        this.firstName = doctor.getFirstName();
        this.specialty = doctor.getSpecialty();
        this.city = doctor.getCity();
        this.password = doctor.getPassword();
    }

    public String getPermitNumber() {
        return permitNumber;
    }

    public void setPermitNumber(String permitNumber) {
        this.permitNumber = permitNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "permitNumber='" + permitNumber + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", specialty='" + specialty + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
