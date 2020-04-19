package com.example.whatsapp.model;

import java.util.HashMap;
import java.util.Map;

public class $_UserModel {
    private String id;
    private String email;
    private String name;
    private String status;
    private String image;

    public $_UserModel(String id, String email, String name, String status, String image) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.status = status;
        this.image = image;
    }

    public Map<String, String> map() {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", this.id);
        map.put("email", this.email);
        map.put("name", this.name);
        map.put("status", this.status);
        map.put("image", this.image);
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
