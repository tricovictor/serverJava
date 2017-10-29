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
    private int surveys_id;
    private int level_id;

    public Score() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSurveys_id() {
        return surveys_id;
    }

    public void setSurveys_id(int surveys_id) {
        this.surveys_id = surveys_id;
    }

    public int getLevel_id() {
        return level_id;
    }

    public void setLevel_id(int level_id) {
        this.level_id = level_id;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", surveys_id=" + surveys_id +
                ", level_id=" + level_id +
                '}';
    }
}
