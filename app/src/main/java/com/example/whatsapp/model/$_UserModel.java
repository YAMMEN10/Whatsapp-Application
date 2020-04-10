package com.example.whatsapp.model;

public class $_UserModel {
    private String uid;
    private String email;

    public $_UserModel(String uid, String email) {
        this.uid = uid;
        this.email = email;
    }

    // Getter and Setter

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
