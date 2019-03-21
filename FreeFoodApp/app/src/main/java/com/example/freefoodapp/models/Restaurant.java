package com.example.freefoodapp.models;

import java.util.Arrays;
import java.util.List;

public class Restaurant {

    private String id;
    private String name;
    private String address;
    private List<Intolerance> intolerance;
    private String timetable;
    private String loc;
    private String picture;
    private String description;

    public Restaurant(){}

    public Restaurant(String id, String name, String address, List<Intolerance> intolerance, String timetable, String loc, String picture, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.intolerance = intolerance;
        this.timetable = timetable;
        this.loc = loc;
        this.picture = picture;
        this.description = description;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Intolerance> getIntolerance() {
        return intolerance;
    }

    public void setIntolerance(List<Intolerance> intolerance) {
        this.intolerance = intolerance;
    }

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", intolerance=" + intolerance +
                ", timetable='" + timetable + '\'' +
                ", loc='" + loc + '\'' +
                ", picture='" + picture + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
