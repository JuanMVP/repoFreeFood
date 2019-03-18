package com.example.freefoodapp.models;

public class Picture {

    private String id;
    private String imgurLink;

    public Picture(){}

    public Picture(String id, String imgurLink) {
        this.id = id;
        this.imgurLink = imgurLink;
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

    @Override
    public String toString() {
        return "Picture{" +
                "id='" + id + '\'' +
                ", imgurLink='" + imgurLink + '\'' +
                '}';
    }
}
