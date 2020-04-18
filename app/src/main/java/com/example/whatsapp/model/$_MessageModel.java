package com.example.whatsapp.model;

import java.util.HashMap;
import java.util.Map;

public class $_MessageModel {
    private String name;
    private String message;
    private String date;
    private String time;

    public $_MessageModel(String name, String message, String date, String time) {
        this.name = name;
        this.message = message;
        this.date = date;
        this.time = time;
    }

    public $_MessageModel(Map map) {
        this.name = map.get("name").toString();
        this.message = map.get("message").toString();
        this.date = map.get("date").toString();
        this.time = map.get("time").toString();
    }

    public Map<String, String> toMap(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", this.name);
        map.put("message", this.message);
        map.put("date", this.date);
        map.put("time", this.time);
        return map;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
