package com.example.whatsapp.model;

import java.util.HashMap;
import java.util.Map;

public class $_GroupInformationModel {
    private String name;

    public $_GroupInformationModel(String group_name) {
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
