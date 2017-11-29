package com.smartcity.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by victor on 29/11/17.
 */
@Entity
public class SubAmbitoTipology {
    @Id
    @GeneratedValue
    private int id;
    private String nameTipology;
    private String description;
    private String descriptionExtra;
    private String subAmbitos;
    private int score;

    public SubAmbitoTipology() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTipology() {
        return nameTipology;
    }

    public void setNameTipology(String nameTipology) {
        this.nameTipology = nameTipology;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionExtra() {
        return descriptionExtra;
    }

    public void setDescriptionExtra(String descriptionExtra) {
        this.descriptionExtra = descriptionExtra;
    }

    public String getSubAmbitos() {
        return subAmbitos;
    }

    public void setSubAmbitos(String subAmbitos) {
        this.subAmbitos = subAmbitos;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "SubAmbitoTipology{" +
                "id=" + id +
                ", nameTipology='" + nameTipology + '\'' +
                ", description='" + description + '\'' +
                ", descriptionExtra='" + descriptionExtra + '\'' +
                ", subAmbitos='" + subAmbitos + '\'' +
                ", score=" + score +
                '}';
    }
}
