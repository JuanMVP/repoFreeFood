package com.example.freefoodapp.models;

public class Restaurant {

    private String id;
    private String name;
    private String address;
    private String intolerance;
    private String timeTable;
    private String loc;

    public Restaurant(){}

    public Restaurant(String id, String name, String address, String intolerance, String timeTable, String loc) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.intolerance = intolerance;
        this.timeTable = timeTable;
        this.loc = loc;
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

    public String getIntolerance() {
        return intolerance;
    }

    public void setIntolerance(String intolerance) {
        this.intolerance = intolerance;
    }

    public String getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(String timeTable) {
        this.timeTable = timeTable;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }


    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", intolerance='" + intolerance + '\'' +
                ", timeTable='" + timeTable + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}
