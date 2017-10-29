package com.smartcity.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.security.Timestamp;

@Entity
public class Ambito {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    public Ambito() {
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
        return "Ambito{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}