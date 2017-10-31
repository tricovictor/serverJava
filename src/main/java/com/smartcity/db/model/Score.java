package com.smartcity.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.security.Timestamp;

@Entity
public class Score {
    @Id
    @GeneratedValue
    private int id;
    private int surveysId;
    private int levelId;

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

    public int getSurveysId() {
        return surveysId;
    }

    public void setSurveysId(int surveysId) {
        this.surveysId = surveysId;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", surveysId=" + surveysId +
                ", levelId=" + levelId +
                '}';
    }
}
