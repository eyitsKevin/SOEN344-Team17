package com.soen344.ubersante.models.clinic;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

import com.soen344.ubersante.models.*;

@Entity
@Table(name = "clinic")
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @NotNull
    private long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

    @NotNull
    @OneToMany
    @JoinColumn(name = "assigned_doctors", table = "doctor", nullable = false)
    private List<Doctor> list_doctor = new ArrayList<Doctor>(7);

    @NotNull
    @OneToMany
    @JoinColumn(name = "assigned_nurses", table = "nurse", nullable = false)
    private List<Nurse> list_nurse = new ArrayList<Nurse>();

    @NotNull
    @OneToMany
    @JoinColumn(name = "appointments", table = "appointment", nullable = false)
    private List<Appointment> list_appointments = new ArrayList<Appointment>();

    @NotNull
    @OneToMany
    @JoinColumn(name = "availabilities", table = "availability", nullable = false)
    private List<Availability> list_availabilities = new ArrayList<Availability>();

    @NotNull
    @OneToMany
    @JoinColumn(name = "rooms", table = "room", nullable = false)
    private List<Room> list_rooms = new ArrayList<Room>();

    @NotNull
    @Temporal(TemporalType.TIME)
    @Column(name = "opening_hour", nullable = false)
    private Date opening_hour;

    @NotNull
    @Temporal(TemporalType.TIME)
    @Column(name = "closing_hour", nullable = false)
    private Date closing_hour;
    
}
