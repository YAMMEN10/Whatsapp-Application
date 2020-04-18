package com.example.whatsapp.model;

import java.util.HashMap;
import java.util.Map;

public class $_UserModel {
    private String id;
    private String email;
    private String name;
    private String status;
    private String picture;

    public $_UserModel(String id, String email, String name, String status, String picture) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.status = status;
        this.picture = picture;
    }

    public Map<String, String> map() {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", this.id);
        map.put("email", this.email);
        map.put("name", this.name);
        map.put("status", this.status);
        map.put("picture", this.picture);
        return map;
    }

    // Getter and setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
