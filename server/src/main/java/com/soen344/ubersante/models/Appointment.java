package com.soen344.ubersante.models;

import com.soen344.ubersante.enums.AppointmentType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @NotNull
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @NotNull
    @Column(name = "createdBy", nullable = false)
    private String createdBy;

    @NotNull
    @Column(name = "appointment_type", nullable = false)
    private AppointmentType appointmentType;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @NotNull
    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @NotNull
    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Clinic clinic;

    public Appointment() { }

    public Appointment(@NotNull Patient patient, @NotNull Doctor doctor, @NotNull String createdBy, @NotNull AppointmentType appointmentType, @NotNull LocalDateTime date, @NotNull LocalDateTime time, @NotNull Timestamp createdAt, @NotNull Clinic clinic) {
        this.patient = patient;
        this.doctor = doctor;
        this.createdBy = createdBy;
        this.appointmentType = appointmentType;
        this.date = date;
        this.time = time;
        this.createdAt = createdAt;
        this.clinic = clinic;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;
        Appointment that = (Appointment) o;
        return getId() == that.getId() &&
                Objects.equals(getPatient(), that.getPatient()) &&
                Objects.equals(getDoctor(), that.getDoctor()) &&
                Objects.equals(getCreatedBy(), that.getCreatedBy()) &&
                getAppointmentType() == that.getAppointmentType() &&
                Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getTime(), that.getTime()) &&
                Objects.equals(getCreatedAt(), that.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPatient(), getDoctor(), getCreatedBy(), getAppointmentType(), getDate(), getTime(), getCreatedAt());
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", createdBy='" + createdBy + '\'' +
                ", appointmentType=" + appointmentType +
                ", date=" + date +
                ", time=" + time +
                ", createdAt=" + createdAt +
                '}';
    }
}