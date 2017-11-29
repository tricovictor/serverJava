package com.smartcity.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by victor on 29/11/17.
 */
@Entity
public class TipologyOk {
    @Id
    @GeneratedValue
    private int id;
    private int percentage;

    public TipologyOk() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "TipologyOk{" +
                "id=" + id +
                ", percentage=" + percentage +
                '}';
    }
}
