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

    @Override
    public String toString() {
        return "Availability{" +
                "id=" + id +
                ", doctorPermitNumber='" + doctorPermitNumber + '\'' +
                ", appointmentType=" + appointmentType +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Availability)) return false;
        Availability that = (Availability) o;
        return id == that.id &&
                Objects.equals(doctorPermitNumber, that.doctorPermitNumber) &&
                appointmentType == that.appointmentType &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctorPermitNumber, appointmentType, startTime, endTime);
    }
}
