package com.smartcity.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Degree {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int typelevelId;

    @OneToMany(mappedBy = "degree", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Score> scores = new ArrayList<>();

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

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
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
