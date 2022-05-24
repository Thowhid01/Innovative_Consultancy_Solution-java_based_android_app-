package com.efty.innovativeconsultancysolutions;

public class Blog {
    private String body;
    private String catahgory;
    private String email;
    private String image;
    private String keyword;
    private String title;

    public Blog() {
    }

    public Blog(String body, String catahgory, String email, String image, String keyword, String title) {
        this.body = body;
        this.catahgory = catahgory;
        this.email = email;
        this.image = image;
        this.keyword = keyword;
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCatahgory() {
        return catahgory;
    }

    public void setCatahgory(String catahgory) {
        this.catahgory = catahgory;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
