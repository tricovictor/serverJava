package com.smartcity.db.model;


import javax.persistence.*;

@Entity
public class Graphic {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String  labels;
    private String data;
    private Integer groupId;
    private Integer surveyId;

    public Graphic() {
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

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    @Override
    public String toString() {
        return "Graphic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", labels='" + labels + '\'' +
                ", data='" + data + '\'' +
                ", groupId=" + groupId +
                '}';
    }
}