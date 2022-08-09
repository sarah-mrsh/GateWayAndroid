package com.example.fanp.presentation.node.add;

import com.example.fanp.domain.local.repository.I4AllSetting;

import java.util.ArrayList;
import java.util.List;

public class SpinnerModel {


    String name;
    String type;
    String id;
    List<I4AllSetting> tags = new ArrayList<>();


    public SpinnerModel(String name, String type, String id, List<I4AllSetting> tags) {
        this.name = name;
        this.type = type;
        this.id = id;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<I4AllSetting> getTags() {
        return tags;
    }

    public void setTags(List<I4AllSetting> tags) {
        this.tags = tags;
    }
}
