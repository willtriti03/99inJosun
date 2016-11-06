package com.example.jungjune.sunrintons.Activitys;

import java.sql.Date;

/**
 * Created by Jaehyun on 2016-07-23.
 */
public class GongooSchema {
    private String id;
    private Date time;
    private String title;
    private String image;
    private String name;
    private String location;
    private int money;
    private String info;
    private boolean complete;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GongooSchema(String name) {
        this.name = name;
    }
}
