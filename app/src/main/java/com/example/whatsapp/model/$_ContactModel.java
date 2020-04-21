package com.example.whatsapp.model;

public class $_ContactModel {
    private String name;
    private String status;
    private String image;

    public $_ContactModel(String name, String status, String image) {
        this.name = name;
        this.status = status;
        this.image = image;
    }

    public $_ContactModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
