package com.smartcity.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class SubAmbito {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private boolean state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ambitoId")
    @JsonIgnore
    private Ambito ambito;


    public SubAmbito() {
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

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Ambito getAmbito() {
        return ambito;
    }

    public void setAmbito(Ambito ambito) {
        this.ambito = ambito;
    }

    @Override
    public String toString() {
        return "SubAmbito{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", ambito=" + ambito +
                '}';
    }
}
