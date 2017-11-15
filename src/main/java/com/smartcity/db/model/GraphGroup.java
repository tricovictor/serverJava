package com.smartcity.db.model;


import javax.persistence.*;

@Entity
public class GraphGroup {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer ambitoId;

    public GraphGroup() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmbitoId() {
        return ambitoId;
    }

    public void setAmbitoId(Integer ambitoId) {
        this.ambitoId = ambitoId;
    }

    @Override
    public String toString() {
        return "GraphGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ambitoId=" + ambitoId +
                '}';
    }
}