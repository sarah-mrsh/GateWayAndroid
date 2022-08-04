package com.example.fanp.presentation.node.add;

public class NodeTagModel {



    String alternative;
    String tag;
    String mode;


    public NodeTagModel(String alternative, String tag, String mode) {
        this.alternative = alternative;
        this.tag = tag;
        this.mode = mode;
    }


    public String getAlternative() {
        return alternative;
    }

    public void setAlternative(String alternative) {
        this.alternative = alternative;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String isMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
