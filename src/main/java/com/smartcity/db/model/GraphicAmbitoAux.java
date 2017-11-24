package com.smartcity.db.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GraphicAmbitoAux {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String labels;
    private String data;

    public GraphicAmbitoAux() {
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

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}