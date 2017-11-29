package com.smartcity.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by victor on 29/11/17.
 */
@Entity
public class SubAmbitoBusiness {
    @Id
    @GeneratedValue
    private int id;
    private int subAmbitoId;
    private int businessId;

    public SubAmbitoBusiness() {
    }

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

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    @Override
    public String toString() {
        return "SubAmbitoBusiness{" +
                "id=" + id +
                ", subAmbitoId=" + subAmbitoId +
                ", businessId=" + businessId +
                '}';
    }
}
