package com.jaybill.billblog.pojo;

import java.sql.Timestamp;

public class Image {
    private Long imageId;

    private Long userId;

    private String imageAlbum;

    private String imagePath;

    private Timestamp imageDatetime;

    private Byte imageState;

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getImageAlbum() {
        return imageAlbum;
    }

    public void setImageAlbum(String imageAlbum) {
        this.imageAlbum = imageAlbum == null ? null : imageAlbum.trim();
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    public Timestamp getImageDatetime() {
        return imageDatetime;
    }

    public void setImageDatetime(Timestamp imageDatetime) {
        this.imageDatetime = imageDatetime;
    }

    public Byte getImageState() {
        return imageState;
    }

    public void setImageState(Byte imageState) {
        this.imageState = imageState;
    }
}