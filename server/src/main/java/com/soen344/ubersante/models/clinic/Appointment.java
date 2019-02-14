package com.soen344.ubersante.models.clinic;

import com.soen344.ubersante.enums.AppointmentType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

import com.soen344.ubersante.models.*;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @NotNull
    private long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "patient_id", table = "patient", nullable = false)
    private Patient patient;

    @NotNull
    @OneToOne
    @JoinColumn(name = "doctor_id", table = "doctor", nullable = false)
    private Doctor doctor;

    @NotNull
    @Column(name = "createdBy", nullable = false)
    private String createdBy;

    @NotNull
    @Column(name = "appointment_type", nullable = false)
    private AppointmentType appointmentType;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private Date date;

    @NotNull
    @Temporal(TemporalType.TIME)
    @Column(name = "time", nullable = false)
    private Date time;

    @NotNull
    @JoinColumn(name = "clinic_id", table = "clinic", nullable = false)
    private Clinic affiliatedClinic;

    @NotNull
    @JoinColumn(name = "room_id", table = "room", nullable = false)
    private Room room;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    public Appointment(@NotNull Patient patient, @NotNull Doctor doctor, @NotNull String createdBy, @NotNull AppointmentType appointmentType, @NotNull Date date, @NotNull Date time, @NotNull Clinic affiliatedClinic, @NotNull Room room, @NotNull Timestamp createdAt) {
        this.patient = patient;
        this.doctor = doctor;
        this.createdBy = createdBy;
        this.appointmentType = appointmentType;
        this.date = date;
        this.time = time;
        this.affiliatedClinic = affiliatedClinic;
        this.room = room;
        this.createdAt = createdAt;
    }

    public Appointment(Appointment appointment) {
        this.patient = appointment.patient;
        this.doctor = appointment.doctor;
        this.createdBy = appointment.createdBy;
        this.appointmentType = appointment.appointmentType;
        this.date = appointment.date;
        this.time = appointment.time;
        this.affiliatedClinic = appointment.affiliatedClinic;
        this.room = appointment.room;
        this.createdAt = appointment.createdAt;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Clinic getAffiliatedClinic() {
        return affiliatedClinic;
    }

    public void setAffiliatedClinic(Clinic affiliatedClinic) {
        this.affiliatedClinic = affiliatedClinic;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
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
                Objects.equals(getAffiliatedClinic(), that.getAffiliatedClinic()) &&
                Objects.equals(getRoom(), that.getRoom()) &&
                Objects.equals(getCreatedAt(), that.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPatient(), getDoctor(), getCreatedBy(), getAppointmentType(), getDate(), getTime(), getAffiliatedClinic(), getRoom(), getCreatedAt());
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + getId() +
                ", patient=" + getPatient() +
                ", doctor=" + getDoctor() +
                ", createdBy='" + getCreatedBy() + '\'' +
                ", appointmentType=" + getAppointmentType() +
                ", date=" + getDate() +
                ", time=" + getTime() +
                ", affiliatedClinic=" + getAffiliatedClinic() +
                ", room=" + getRoom() +
                ", createdAt=" + getCreatedAt() +
                '}';
    }
}
