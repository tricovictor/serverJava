package com.smartcity.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.security.Timestamp;

@Entity
public class Degrees {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int subambito_id;

    public Degrees() {
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

    public int getSubambito_id() {
        return subambito_id;
    }

    public void setSubambito_id(int subambito_id) {
        this.subambito_id = subambito_id;
    }

    @Override
    public String toString() {
        return "Degrees{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subambito_id=" + subambito_id +
                '}';
    }
}
