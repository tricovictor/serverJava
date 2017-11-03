package com.smartcity.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Survey {
    @Id
    @GeneratedValue
    private int id;
    private Timestamp initialdate;
    private Timestamp finaldate;
    private String state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "municipalityId")
    private Municipality municipality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<Score> scores = new ArrayList<>();

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    @Override
    public String toString() {
        return "Survey{" +
                "id=" + id +
                ", initialdate=" + initialdate +
                ", finaldate=" + finaldate +
                ", state='" + state + '\'' +
                '}';
    }
}
