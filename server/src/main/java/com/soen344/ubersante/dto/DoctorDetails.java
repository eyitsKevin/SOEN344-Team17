package com.soen344.ubersante.dto;

import com.soen344.ubersante.models.Doctor;
import com.soen344.ubersante.validation.ValidPermitNumber;

import javax.validation.constraints.NotEmpty;

public class DoctorDetails {

    @ValidPermitNumber
    private String permitNumber;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String specialty;

    @NotEmpty
    private String city;

    private long clinicId;
    
    @NotEmpty
    private String clinicName;

    public DoctorDetails(Doctor doctor) {
        permitNumber = doctor.getPermitNumber();
        lastName = doctor.getLastName();
        firstName = doctor.getFirstName();
        specialty = doctor.getSpecialty();
        city = doctor.getCity();
        clinicName = doctor.getClinic().getName();
    }

    public String getPermitNumber() {
        return permitNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getCity() {
        return city;
    }

    public void setPermitNumber(String permitNumber) {
        this.permitNumber = permitNumber;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSpecialty(String birthday) {
        this.specialty = specialty;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getClinicId() {
        return clinicId;
    }

    public void setClinicId(long clinicId) {
        this.clinicId = clinicId;

    }
    
    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    @Override
    public String toString() {
        return "DoctorDetails{" +
                "permitNumber='" + permitNumber + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", specialty='" + specialty + '\'' +
                ", city='" + city + '\'' +
                ", clinicName='" + clinicName + '\'' +
                '}';
    }
}
