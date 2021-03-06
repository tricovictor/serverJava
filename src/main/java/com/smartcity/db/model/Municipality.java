package com.smartcity.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.security.Timestamp;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Municipality {
    @Id
    @GeneratedValue
    private int id;

    @NotNull(message = "")
    private String name;

    @NotNull(message = "")
    private String idioms;

    private Double latitude;

    private Double longitude;

    @NotNull
    private int habitants;

    @NotNull
    private String intendent;

    @NotNull
    private String alcalde;

    @NotNull
    private int superficie;

    @NotNull
    private String website;

    @NotNull
    private int departmentId;

    @NotNull
    private int tipologyId;

   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departmentId")
    @JsonIgnore
    private Department department;
*/
    public Municipality() {
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

    public String getIdioms() {
        return idioms;
    }

    public void setIdioms(String idioms) {
        this.idioms = idioms;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public int getHabitants() {
        return habitants;
    }

    public void setHabitants(int habitants) {
        this.habitants = habitants;
    }

    public String getIntendent() {
        return intendent;
    }

    public void setIntendent(String intendent) {
        this.intendent = intendent;
    }

    public String getAlcalde() {
        return alcalde;
    }

    public void setAlcalde(String alcalde) {
        this.alcalde = alcalde;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getTipologyId() {
        return tipologyId;
    }

    public void setTipologyId(int tipologyId) {
        this.tipologyId = tipologyId;
    }

    /*  public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
*/

    @Override
    public String toString() {
        return "Municipality{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idioms='" + idioms + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", habitants=" + habitants +
                ", intendent='" + intendent + '\'' +
                ", alcalde='" + alcalde + '\'' +
                ", superficie=" + superficie +
                ", website='" + website + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }
}
