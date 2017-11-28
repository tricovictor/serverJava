package com.smartcity.db.model;

import java.io.Serializable;

public class Response implements Serializable {
    private String response;

    public Response() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
