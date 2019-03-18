package com.example.freefoodapp.models;

import java.util.List;

public class Restaurant {

    private String id;
    private String name;
    private String address;
    private Intolerance intolerance;
    private String timetable;
    private String loc;
    private List<String> listPhotos;

    public Restaurant(){}

    public Restaurant(String id, String name, String address, Intolerance intolerance, String timetable, String loc, List<String> listPhotos) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.intolerance = intolerance;
        this.timetable = timetable;
        this.loc = loc;
        this.listPhotos = listPhotos;
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

    public Intolerance getIntolerance() {
        return intolerance;
    }

    public void setIntolerance(Intolerance intolerance) {
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

    public List<String> getListPhotos() {
        return listPhotos;
    }

    public void setListPhotos(List<String> listPhotos) {
        this.listPhotos = listPhotos;
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
                ", listPhotos=" + listPhotos +
                '}';
    }
}
