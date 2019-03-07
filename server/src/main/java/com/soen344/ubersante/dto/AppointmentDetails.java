package com.soen344.ubersante.dto;

import com.soen344.ubersante.enums.AppointmentType;
import com.soen344.ubersante.models.Doctor;
import com.soen344.ubersante.models.Patient;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * Appointment
 */
public class AppointmentDetails {

    @NotEmpty
    private PatientDetails patientDetails;

    @NotEmpty
    private DoctorDetails doctorDetails;

    @NotEmpty
    private String createdBy;

    @NotEmpty
    private AppointmentType appointmentType;

    @NotEmpty
    private String date;

    @NotEmpty
    private String time;

    @NotEmpty
    private String createdAt;

    public AppointmentDetails(@NotEmpty Patient patient, @NotEmpty Doctor doctor, @NotEmpty String createdBy, @NotEmpty AppointmentType appointmentType, @NotEmpty String date, @NotEmpty String time, @NotEmpty String createdAt) {
        this.patientDetails = new PatientDetails(patient);
        this.doctorDetails = new DoctorDetails(doctor);
        this.createdBy = createdBy;
        this.appointmentType = appointmentType;
        this.date = date;
        this.time = time;
        this.createdAt = createdAt;
    }

    public PatientDetails getPatientDetails() {
        return patientDetails;
    }

    public void setPatientDetails(PatientDetails patientDetails) {
        this.patientDetails = patientDetails;
    }

    public DoctorDetails getDoctorDetails() {
        return doctorDetails;
    }

    public void setDoctorDetails(DoctorDetails doctorDetails) {
        this.doctorDetails = doctorDetails;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppointmentDetails)) return false;
        AppointmentDetails that = (AppointmentDetails) o;
        return Objects.equals(getPatientDetails(), that.getPatientDetails()) &&
                Objects.equals(getDoctorDetails(), that.getDoctorDetails()) &&
                Objects.equals(getCreatedBy(), that.getCreatedBy()) &&
                getAppointmentType() == that.getAppointmentType() &&
                Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getTime(), that.getTime()) &&
                Objects.equals(getCreatedAt(), that.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPatientDetails(), getDoctorDetails(), getCreatedBy(), getAppointmentType(), getDate(), getTime(), getCreatedAt());
    }

    @Override
    public String toString() {
        return "AppointmentDetails{" +
                "patientDetails=" + patientDetails +
                ", doctorDetails=" + doctorDetails +
                ", createdBy='" + createdBy + '\'' +
                ", appointmentType=" + appointmentType +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
