package com.example.whatsapp.model;

import java.util.HashMap;
import java.util.Map;

public class $_GroupInformation {
    private String name;

    public $_GroupInformation(String group_name) {
        this.name = group_name;
    }

    public Map<String, String> map() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", this.name);
        return map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
