package com.smartcity.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.security.Timestamp;

@Entity
public class Degree {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int typelevelId;

    public Degree() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypelevelId() {
        return typelevelId;
    }

    public void setTypelevelId(int typelevelId) {
        this.typelevelId = typelevelId;
    }

    @Override
    public String toString() {
        return "Degree{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typelevelId=" + typelevelId +
                '}';
    }
}
