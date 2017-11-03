package com.smartcity.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TypeLevel {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "typeLevel", cascade = CascadeType.ALL)
    private List<Degree> degrees = new ArrayList<>();

    public TypeLevel() {
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

    public List<Degree> getDegrees() {
        return degrees;
    }

    public void setDegrees(List<Degree> degrees) {
        this.degrees = degrees;
    }

    @Override
    public String toString() {
        return "TypeLevel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", degrees=" + degrees +
                '}';
    }
}
