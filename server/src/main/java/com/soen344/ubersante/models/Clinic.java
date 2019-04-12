package com.soen344.ubersante.models;

import com.soen344.ubersante.validation.ValidClinicHours;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "clinic")
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "rooms")
    @Positive
    private int rooms;

    @Embedded
    @ValidClinicHours
    private ClinicHours clinicHours;

    public Clinic() {}

    public Clinic(String name, int rooms, ClinicHours clinicHours) {
        this.name = name;
        this.rooms = rooms;
        this.clinicHours = clinicHours;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRooms() {
        return rooms;
    }

    public ClinicHours getClinicHours() {
        return clinicHours;
    }

    public LocalTime openTime() {
        return clinicHours.getOpen();
    }

    public LocalTime closeTime() {
        return clinicHours.getClose();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public void setClinicHours(@ValidClinicHours ClinicHours clinicHours) {
        this.clinicHours = clinicHours;
    }

    @Override
    public String toString() {
        return "Clinic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rooms=" + rooms +
                ", clinicHours=" + clinicHours +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clinic)) return false;
        Clinic clinic = (Clinic) o;
        return id == clinic.id &&
                rooms == clinic.rooms &&
                name.equals(clinic.name) &&
                clinicHours.equals(clinic.clinicHours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rooms, clinicHours);
    }
}
