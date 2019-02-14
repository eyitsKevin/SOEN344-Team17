package com.soen344.ubersante.models;

import com.soen344.ubersante.validation.ValidAccessID;

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
    @ValidAccessID
    private String accessID;

    @Column(name = "password", length = 60)
    @NotEmpty
    private String password;

    public Nurse() {}

    public Nurse(String accessID, String password) {
        this.accessID = accessID;
        this.password = password;
    }

    public Nurse(Nurse nurse) {
        this.accessID = nurse.getAccessID();
        this.password = nurse.getPassword();
    }

    public String getAccessID() {
        return accessID;
    }

    public void setAccessID(String accessID) {
        this.accessID = accessID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "accessID='" + accessID + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (!(o instanceof Nurse)) return false;
        Nurse nurse = (Nurse) o;
        return accessID.equals(nurse.accessID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessID);
    }
}
