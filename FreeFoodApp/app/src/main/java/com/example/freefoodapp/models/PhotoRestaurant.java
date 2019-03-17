package com.example.freefoodapp.models;

public class PhotoRestaurant {

    private String id;
    private String imgurLink;
    private String deleteHash;


    public PhotoRestaurant(){}

    public PhotoRestaurant(String id, String imgurLink, String deleteHash) {
        this.id = id;
        this.imgurLink = imgurLink;
        this.deleteHash = deleteHash;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgurLink() {
        return imgurLink;
    }

    public void setImgurLink(String imgurLink) {
        this.imgurLink = imgurLink;
    }

    public String getDeleteHash() {
        return deleteHash;
    }

    public void setDeleteHash(String deleteHash) {
        this.deleteHash = deleteHash;
    }


    @Override
    public String toString() {
        return "PhotoRestaurant{" +
                "id='" + id + '\'' +
                ", imgurLink='" + imgurLink + '\'' +
                ", deleteHash='" + deleteHash + '\'' +
                '}';
    }
}
