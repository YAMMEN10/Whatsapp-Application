package com.example.whatsapp.model;

public class $_ContactsKey {
    private String key;
    private String request_type;

    public $_ContactsKey(String contacts, String request_type) {
        this.key = contacts;
        this.request_type = request_type;
    }

    public $_ContactsKey() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRequest_type() {
        return request_type;
    }

    public void setRequest_type(String request_type) {
        this.request_type = request_type;
    }
}
