package com.example.freefoodapp.responses;

public class RecetaResponse {

    private String name;
    private String descripcion;
    private String ingredients;
    private Integer dinnerGuest;
    private String picture;

    public RecetaResponse(){}

    public RecetaResponse(String name, String descripcion, String ingredients, Integer dinnerGuest, String picture) {
        this.name = name;
        this.descripcion = descripcion;
        this.ingredients = ingredients;
        this.dinnerGuest = dinnerGuest;
        this.picture = picture;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getDinnerGuest() {
        return dinnerGuest;
    }

    public void setDinnerGuest(Integer dinnerGuest) {
        this.dinnerGuest = dinnerGuest;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    @Override
    public String toString() {
        return "RecetaResponse{" +
                "name='" + name + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", dinnerGuest=" + dinnerGuest +
                ", picture='" + picture + '\'' +
                '}';
    }
}
