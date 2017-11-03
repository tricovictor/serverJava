package com.smartcity.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ambito {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private boolean state;

    @OneToMany(mappedBy = "ambito", cascade = CascadeType.ALL)
    private List<SubAmbito> subAmbitos = new ArrayList<>();


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

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public List<SubAmbito> getSubAmbitos() {
        return subAmbitos;
    }

    public void setSubAmbitos(List<SubAmbito> subAmbitos) {
        this.subAmbitos = subAmbitos;
    }

    @Override
    public String toString() {
        return "Ambito{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}