package com.example;

import java.io.Serializable;

public class Watermark implements Serializable {
    private static final long serialVersionUID = 1L;

    private String appid;

    private int timestamp;

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppid() {
        return appid;
    }
}