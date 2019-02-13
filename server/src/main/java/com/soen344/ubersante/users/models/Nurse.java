package com.soen344.ubersante.users.models;

import javax.persistence.*;


@Entity
@Table(name = "nurse")
public class Nurse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "access_id")
    private String accessID;

    @Column(name = "password")
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
}
