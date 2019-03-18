package com.example.freefoodapp.responses;

import com.example.freefoodapp.models.Intolerance;

import java.util.List;

public class RestaurantResponse {

    private String id;
    private String name;
    private String address;
    private Intolerance intoleranceId;
    private String timeTable;
    private String loc;
    private List<String> listaFotos;

    public RestaurantResponse(){}

    public RestaurantResponse(String id, String name, String address, Intolerance intoleranceId, String timeTable, String loc, List<String> listaFotos) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.intoleranceId = intoleranceId;
        this.timeTable = timeTable;
        this.loc = loc;
        this.listaFotos = listaFotos;
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

    public Intolerance getIntoleranceId() {
        return intoleranceId;
    }

    public void setIntoleranceId(Intolerance intoleranceId) {
        this.intoleranceId = intoleranceId;
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

    public List<String> getListaFotos() {
        return listaFotos;
    }

    public void setListaFotos(List<String> listaFotos) {
        this.listaFotos = listaFotos;
    }


    @Override
    public String toString() {
        return "RestaurantResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", intoleranceId=" + intoleranceId +
                ", timeTable='" + timeTable + '\'' +
                ", loc='" + loc + '\'' +
                ", listaFotos=" + listaFotos +
                '}';
    }
}
