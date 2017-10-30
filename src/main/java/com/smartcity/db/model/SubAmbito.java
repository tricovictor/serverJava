package com.smartcity.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SubAmbito {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int ambito_id;
    private boolean state;

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

    public int getAmbito_id() {
        return ambito_id;
    }

    public void setAmbito_id(int ambito_id) {
        this.ambito_id = ambito_id;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "SubAmbito{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ambito_id=" + ambito_id +
                '}';
    }
}
