package com.smartcity.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.security.Timestamp;

@Entity
public class Survey {
    @Id
    @GeneratedValue
    private int id;
    private Timestamp initialdate;
    private Timestamp finaldate;
    private String state;
    private int user_id;
    private int municipality_id;

    public Survey() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getInitialdate() {
        return initialdate;
    }

    public void setInitialdate(Timestamp initialdate) {
        this.initialdate = initialdate;
    }

    public Timestamp getFinaldate() {
        return finaldate;
    }

    public void setFinaldate(Timestamp finaldate) {
        this.finaldate = finaldate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMunicipality_id() {
        return municipality_id;
    }

    public void setMunicipality_id(int municipality_id) {
        this.municipality_id = municipality_id;
    }

    @Override
    public String toString() {
        return "Survey{" +
                "id=" + id +
                ", initialdate=" + initialdate +
                ", finaldate=" + finaldate +
                ", state='" + state + '\'' +
                ", user_id=" + user_id +
                ", municipality_id=" + municipality_id +
                '}';
    }
}
