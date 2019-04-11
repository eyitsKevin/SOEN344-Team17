package com.soen344.ubersante.models;

import com.soen344.ubersante.enums.AppointmentType;
import com.soen344.ubersante.validation.ValidAvailabilityDuration;
import com.soen344.ubersante.validation.ValidPermitNumber;
import com.soen344.ubersante.validation.ValidScheduleTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "doctor_availability")
@ValidAvailabilityDuration
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "doctor_permit_number")
    @ValidPermitNumber
    private String doctorPermitNumber;

    @Column(name = "appointmentType")
    @NotNull
    private AppointmentType appointmentType;

    @Column(name = "start_time")
    @ValidScheduleTime
    private LocalDateTime startTime;

    @Column(name = "end_time")
    @ValidScheduleTime
    private LocalDateTime endTime;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Clinic clinic;

    public Availability() {

    }

    public long getId() {
        return id;
    }

    public String getDoctorPermitNumber() {
        return doctorPermitNumber;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDoctorPermitNumber(String doctorPermitNumber) {
        this.doctorPermitNumber = doctorPermitNumber;
    }

    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
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
        if (!(o instanceof Availability)) return false;
        Availability that = (Availability) o;
        return getId() == that.getId() &&
                Objects.equals(getDoctorPermitNumber(), that.getDoctorPermitNumber()) &&
                getAppointmentType() == that.getAppointmentType() &&
                Objects.equals(getStartTime(), that.getStartTime()) &&
                Objects.equals(getEndTime(), that.getEndTime()) &&
                Objects.equals(getAppointment(), that.getAppointment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctorPermitNumber, appointmentType, startTime, endTime);
    }

    @Override
    public String toString() {
        return "Availability{" +
                "id=" + id +
                ", doctorPermitNumber='" + doctorPermitNumber + '\'' +
                ", appointmentType=" + appointmentType +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", appointment=" + appointment +
                '}';
    }
}
