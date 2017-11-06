package com.smartcity.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class SubAmbitoTypeLevel {

    @Id
    @GeneratedValue
    private int id;
    private int subAmbitoId;
    private int typeLevelId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubAmbitoId() {
        return subAmbitoId;
    }

    public void setSubAmbitoId(int subAmbitoId) {
        this.subAmbitoId = subAmbitoId;
    }

    public int getTypeLevelId() {
        return typeLevelId;
    }


    public void setTypeLevelId(int typeLevelId) {
        this.typeLevelId = typeLevelId;
    }

    @Override
    public String toString() {
        return "SubAmbitoTypeLevel{" +
                "id=" + id +
                ", subAmbitoId=" + subAmbitoId +
                ", typeLevelId=" + typeLevelId +
                '}';
    }
}
