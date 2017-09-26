package com.smartcity.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.security.Timestamp;

@Entity
public class Ambitos {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    public Ambitos() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Ambitos{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}