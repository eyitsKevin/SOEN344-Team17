package com.soen344.ubersante.models;

import com.soen344.ubersante.validation.ValidAccessId;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;


@Entity
@Table(name = "nurse")
public class Nurse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "access_id")
    @ValidAccessId
    private String accessId;

    @Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    private String lastName;

    @Column(name = "password", length = 60)
    @NotEmpty
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Clinic clinic;

    public Nurse() {}

    public Nurse(String accessId, String password) {
        this.accessId = accessId;
        this.password = password;
    }

    public Nurse(Nurse nurse) {
        this.accessId = nurse.getAccessId();
        this.password = nurse.getPassword();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "accessId='" + accessId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (!(o instanceof Nurse)) return false;
        Nurse nurse = (Nurse) o;
        return accessId.equals(nurse.accessId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessId);
    }
}
