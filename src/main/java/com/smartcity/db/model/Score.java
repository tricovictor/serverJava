package com.smartcity.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.security.Timestamp;

@Entity
public class Score {
    @Id
    @GeneratedValue
    private int id;
    private int levelId;

    @ManyToOne
    @JoinColumn(name = "subAmbitoId")
    private SubAmbito subAmbito;


    @ManyToOne
    @JoinColumn(name = "degreeId")
    private Degree degree;

    @ManyToOne
    @JoinColumn(name = "surveyId")
    private Survey survey;

    public Score() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public SubAmbito getSubAmbito() {
        return subAmbito;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public void setSubAmbito(SubAmbito subAmbito) {
        this.subAmbito = subAmbito;
    }
}
