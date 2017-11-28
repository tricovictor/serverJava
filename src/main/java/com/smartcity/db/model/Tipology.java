package com.smartcity.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by victor on 27/11/17.
 */
@Entity
public class Tipology {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    private String descriptionExtra;
    private String subAmbitos;

    public Tipology() {
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

    @Override
    public String toString() {
        return "Tipology{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", descriptionExtra='" + descriptionExtra + '\'' +
                '}';
    }
}
