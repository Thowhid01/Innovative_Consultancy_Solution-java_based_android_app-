package com.efty.innovativeconsultancysolutions;

public class Video {
    private String catagory;
    private String keyword;
    private String thumbnail;
    private String title;
    private String videoDetails;
    private String videolink;

    public Video() {
    }

    public Video(String catagory, String keyword, String thumbnail, String title, String videoDetails, String videolink) {
        this.catagory = catagory;
        this.keyword = keyword;
        this.thumbnail = thumbnail;
        this.title = title;
        this.videoDetails = videoDetails;
        this.videolink = videolink;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoDetails() {
        return videoDetails;
    }

    public void setVideoDetails(String videoDetails) {
        this.videoDetails = videoDetails;
    }

    public String getVideolink() {
        return videolink;
    }

    public void setVideolink(String videolink) {
        this.videolink = videolink;
    }
}
