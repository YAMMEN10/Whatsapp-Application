package com.example.whatsapp.model;

public class $_RequestKey {
    private String request_type;

    public $_RequestKey(String contacts) {
        request_type = contacts;
    }

    public $_RequestKey() {
    }

    public String getRequest_type() {
        return request_type;
    }

    public void setRequest_type(String request_type) {
        this.request_type = request_type;
    }
}
