package com.android.albumslist.model;

import io.realm.RealmObject;

public class Album extends RealmObject {


    private Integer id;
    private String title;
    private String url;
    private String thumbnailUrl;


    public Album(Integer id, String name, String url, String imageurl) {
        this.id = id;
        this.title = name;
        this.url = url;
        this.thumbnailUrl = imageurl;
    }

    public Album() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageurl() {
        return thumbnailUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}