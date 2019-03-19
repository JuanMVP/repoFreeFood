package com.example.freefoodapp.models;

public class Recipe {

    private String name;
    private String description;
    private String ingredients;
    private String dinnerGuest;
    private String picture;

    public Recipe (){}

    public Recipe(String name, String description, String ingredients, String dinnerGuest, String picture) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDinnerGuest() {
        return dinnerGuest;
    }

    public void setDinnerGuest(String dinnerGuest) {
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
        return "Recipe{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", dinnerGuest='" + dinnerGuest + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
